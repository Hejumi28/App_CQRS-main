// co/vinni/cqrs/service/OrderProjectionService.java
package co.vinni.cqrs.service;

import co.vinni.cqrs.dto.OrderEvent;
import co.vinni.cqrs.dto.PromotionEvent;
import co.vinni.cqrs.persistence.entity.*;
import co.vinni.cqrs.persistence.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderProjectionService {
    private final OrderViewRepository orders;
    private final PromotionViewRepository promotions;
    private final InvoiceRepository invoices;

    @KafkaListener(topics = "${kafka.topic.orders}", groupId = "restaurant-orders")
    public void onOrder(OrderEvent e) {
        switch (e.getType()) {
            case ORDER_CREATED -> orders.save(OrderView.builder()
                    .id(e.getOrderId()).customer(e.getCustomer()).kitchen(e.getKitchen())
                    .productCode(e.getProductCode()).quantity(e.getQuantity())
                    .unitPrice(e.getUnitPrice()).status(OrderStatus.PENDING).build());
            case ORDER_ACCEPTED -> {
                var ov = orders.findById(e.getOrderId()).orElseThrow();
                ov.setStatus(OrderStatus.ACCEPTED);
                orders.save(ov);
                createInvoice(ov);
            }
            case ORDER_REJECTED -> {
                var ov = orders.findById(e.getOrderId()).orElseThrow();
                ov.setStatus(OrderStatus.REJECTED);
                orders.save(ov);
            }
        }
    }

    @KafkaListener(topics = "${kafka.topic.promotions}", groupId = "restaurant-promotions")
    public void onPromotion(PromotionEvent e) {
        var list = promotions.findByKitchen(e.getKitchen());
        var pv = list.stream().filter(p -> p.getType()==e.getPromotionType()).findFirst()
                .orElse(PromotionView.builder().kitchen(e.getKitchen()).type(e.getPromotionType()).build());
        pv.setActive(e.isActive());
        pv.setTargetProductCode(e.getTargetProductCode());
        promotions.save(pv);
    }

    private void createInvoice(OrderView o){
        List<PromotionView> active = promotions.findByKitchen(o.getKitchen())
                .stream().filter(PromotionView::isActive).toList();

        BigDecimal subtotal = o.getUnitPrice().multiply(BigDecimal.valueOf(o.getQuantity()));
        BigDecimal discount = BigDecimal.ZERO;
        boolean twoForOneApplied = false;

        // 20% descuento
        if (active.stream().anyMatch(p -> p.getType()==PromotionType.DISCOUNT_20))
            discount = discount.add(subtotal.multiply(new BigDecimal("0.20")));

        // 2x1 por producto
        var twoForOne = active.stream()
                .filter(p -> p.getType()==PromotionType.TWO_FOR_ONE &&
                        o.getProductCode().equalsIgnoreCase(p.getTargetProductCode()))
                .findFirst();
        if (twoForOne.isPresent()){
            twoForOneApplied = true;
            int freeUnits = o.getQuantity() / 2;
            discount = discount.add(o.getUnitPrice().multiply(BigDecimal.valueOf(freeUnits)));
        }

        BigDecimal base = subtotal.subtract(discount).max(BigDecimal.ZERO);
        BigDecimal iva = base.multiply(new BigDecimal("0.19"));
        BigDecimal total = base.add(iva);

        invoices.save(Invoice.builder()
                .orderId(o.getId()).customer(o.getCustomer()).kitchen(o.getKitchen())
                .productCode(o.getProductCode()).quantity(o.getQuantity())
                .unitPrice(o.getUnitPrice()).discount(discount)
                .twoForOneApplied(twoForOneApplied).iva(iva).total(total).build());
    }
}

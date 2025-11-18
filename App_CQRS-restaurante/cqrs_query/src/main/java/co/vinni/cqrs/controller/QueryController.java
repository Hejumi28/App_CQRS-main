// co/vinni/cqrs/controller/QueryController.java
package co.vinni.cqrs.controller;

import co.vinni.cqrs.persistence.entity.*;
import co.vinni.cqrs.persistence.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/query")
@RequiredArgsConstructor
@CrossOrigin(origins="*")
public class QueryController {
    private final OrderViewRepository orders;
    private final InvoiceRepository invoices;
    private final PromotionViewRepository promotions;

    @GetMapping("/orders/pending")
    public List<OrderView> pending(@RequestParam Kitchen kitchen){
        return orders.findByKitchenAndStatus(kitchen, OrderStatus.PENDING);
    }

    @GetMapping("/invoices")
    public List<Invoice> allInvoices(){
        return invoices.findAll();
    }

    @GetMapping("/invoices/{orderId}")
    public Invoice byOrder(@PathVariable Long orderId){
        return invoices.findByOrderId(orderId).orElse(null);
    }

    @GetMapping("/promotions")
    public List<PromotionView> promos(@RequestParam Kitchen kitchen){
        return promotions.findByKitchen(kitchen);
    }
}

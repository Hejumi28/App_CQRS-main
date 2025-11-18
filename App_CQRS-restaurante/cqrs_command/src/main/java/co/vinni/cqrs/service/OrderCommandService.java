package co.vinni.cqrs.service;

import co.vinni.cqrs.dto.CreateOrderRequest;
import co.vinni.cqrs.dto.OrderEvent;
import co.vinni.cqrs.persistence.entity.OrderCmd;
import co.vinni.cqrs.persistence.repository.OrderCmdRepository;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@AllArgsConstructor
public class OrderCommandService {
    private final OrderCmdRepository repo;
    private final KafkaTemplate<String, Object> kafka;

    private static final String TOPIC = "order-events";

    public OrderCmd create(CreateOrderRequest r){
        var order = repo.save(OrderCmd.builder()
                .customer(r.getCustomer())
                .kitchen(r.getKitchen())
                .productCode(r.getProductCode())
                .quantity(r.getQuantity())
                .unitPrice(r.getUnitPrice())
                .status(OrderCmd.OrderStatus.PENDING)
                .build());

        publish(OrderEvent.Type.ORDER_CREATED, order);
        return order;
    }

    public OrderCmd accept(Long id){
        var order = repo.findById(id).orElseThrow();
        order.setStatus(OrderCmd.OrderStatus.ACCEPTED);
        repo.save(order);
        publish(OrderEvent.Type.ORDER_ACCEPTED, order);
        return order;
    }

    public OrderCmd reject(Long id){
        var order = repo.findById(id).orElseThrow();
        order.setStatus(OrderCmd.OrderStatus.REJECTED);
        repo.save(order);
        publish(OrderEvent.Type.ORDER_REJECTED, order);
        return order;
    }

    private void publish(OrderEvent.Type type, OrderCmd o){
        var evt = OrderEvent.builder()
                .type(type)
                .orderId(o.getId())
                .customer(o.getCustomer())
                .kitchen(o.getKitchen())
                .productCode(o.getProductCode())
                .quantity(o.getQuantity())
                .unitPrice(o.getUnitPrice())
                .ts(Instant.now().toEpochMilli())
                .build();
        kafka.send(TOPIC, String.valueOf(o.getId()), evt);
    }
}

package co.vinni.cqrs.dto;

import co.vinni.cqrs.persistence.entity.Kitchen;
import lombok.*;

import java.math.BigDecimal;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class OrderEvent {
    public enum Type { ORDER_CREATED, ORDER_ACCEPTED, ORDER_REJECTED }

    private Type type;
    private Long orderId;
    private String customer;
    private Kitchen kitchen;
    private String productCode;
    private Integer quantity;
    private BigDecimal unitPrice;
    private Long ts;
}

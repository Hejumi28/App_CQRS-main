package co.vinni.cqrs.dto;

import co.vinni.cqrs.persistence.entity.Kitchen;
import lombok.*;

import java.math.BigDecimal;

@Data @AllArgsConstructor @NoArgsConstructor
public class CreateOrderRequest {
    private String customer;
    private Kitchen kitchen;
    private String productCode;
    private Integer quantity;
    private BigDecimal unitPrice;
}

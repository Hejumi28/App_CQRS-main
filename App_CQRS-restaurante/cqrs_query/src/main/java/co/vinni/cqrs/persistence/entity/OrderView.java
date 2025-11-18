package co.vinni.cqrs.persistence.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(collection = "orders")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class OrderView {
    @Id
    private Long id;

    private String customer;
    private Kitchen kitchen;
    private String productCode;
    private Integer quantity;
    private BigDecimal unitPrice;
    private OrderStatus status;
}

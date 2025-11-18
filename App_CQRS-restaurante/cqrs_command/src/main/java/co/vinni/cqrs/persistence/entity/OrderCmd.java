package co.vinni.cqrs.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity @Table(name="orders_command")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class OrderCmd {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customer;

    @Enumerated(EnumType.STRING)
    private Kitchen kitchen;

    private String productCode;
    private Integer quantity;
    private BigDecimal unitPrice;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public enum OrderStatus { PENDING, ACCEPTED, REJECTED }
}

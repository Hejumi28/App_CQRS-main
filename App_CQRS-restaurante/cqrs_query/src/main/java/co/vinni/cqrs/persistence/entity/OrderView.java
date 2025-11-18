// co/vinni/cqrs/persistence/entity/OrderView.java
package co.vinni.cqrs.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity @Table(name="orders_query")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class OrderView {
    @Id private Long id;
    private String customer;
    @Enumerated(EnumType.STRING) private Kitchen kitchen;
    private String productCode;
    private Integer quantity;
    private BigDecimal unitPrice;
    @Enumerated(EnumType.STRING) private OrderStatus status;
}

// co/vinni/cqrs/persistence/entity/Invoice.java
package co.vinni.cqrs.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity @Table(name="invoices_query")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Invoice {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private Long orderId;
    private String customer;
    @Enumerated(EnumType.STRING) private Kitchen kitchen;
    private String productCode;
    private Integer quantity;
    private BigDecimal unitPrice;
    private BigDecimal discount;
    private boolean twoForOneApplied;
    private BigDecimal iva;
    private BigDecimal total;
}

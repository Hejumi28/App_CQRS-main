package co.vinni.cqrs.persistence.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(collection = "invoices")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Invoice {
    @Id
    private Long id;

    private Long orderId;
    private String customer;
    private Kitchen kitchen;
    private String productCode;
    private Integer quantity;
    private BigDecimal unitPrice;
    private BigDecimal discount;
    private boolean twoForOneApplied;
    private BigDecimal iva;
    private BigDecimal total;
}

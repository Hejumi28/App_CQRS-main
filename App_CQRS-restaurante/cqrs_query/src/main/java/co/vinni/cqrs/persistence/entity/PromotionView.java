// co/vinni/cqrs/persistence/entity/PromotionView.java
package co.vinni.cqrs.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name="promotions_query")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class PromotionView {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING) private Kitchen kitchen;
    @Enumerated(EnumType.STRING) private PromotionType type;
    private boolean active;
    private String targetProductCode;
}

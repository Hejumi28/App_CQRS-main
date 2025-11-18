package co.vinni.cqrs.persistence.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "promotions")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class PromotionView {

    @Id
    private String id;

    private Kitchen kitchen;
    private PromotionType type;
    private boolean active;
    private String targetProductCode;
}

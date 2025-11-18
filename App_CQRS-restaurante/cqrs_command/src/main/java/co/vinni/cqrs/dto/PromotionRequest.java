package co.vinni.cqrs.dto;

import co.vinni.cqrs.persistence.entity.Kitchen;
import co.vinni.cqrs.persistence.entity.PromotionType;
import lombok.*;

@Data @AllArgsConstructor @NoArgsConstructor
public class PromotionRequest {
    private Kitchen kitchen;
    private PromotionType promotionType;
    private boolean active;
    private String targetProductCode; // obligatorio si promotionType = TWO_FOR_ONE
}

package co.vinni.cqrs.dto;

import co.vinni.cqrs.persistence.entity.Kitchen;
import co.vinni.cqrs.persistence.entity.PromotionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class PromotionEvent {
    public enum Type { PROMOTION_SET }
    private Type type;
    private Kitchen kitchen;
    private PromotionType promotionType;
    private boolean active;
    private String targetProductCode; // requerido para 2x1
    private Long ts;
}

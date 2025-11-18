package co.vinni.cqrs.service;

import co.vinni.cqrs.dto.PromotionEvent;
import co.vinni.cqrs.dto.PromotionRequest;
import co.vinni.cqrs.persistence.entity.PromotionCmd;
import co.vinni.cqrs.persistence.repository.PromotionCmdRepository;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@AllArgsConstructor
public class PromotionCommandService {
    private final PromotionCmdRepository repo;
    private final KafkaTemplate<String, Object> kafka;
    private static final String TOPIC = "promotion-events";

    public PromotionCmd set(PromotionRequest r){
        var p = repo.findByKitchenAndType(r.getKitchen(), r.getPromotionType())
                .orElse(PromotionCmd.builder().kitchen(r.getKitchen()).type(r.getPromotionType()).build());

        p.setActive(r.isActive());
        p.setTargetProductCode(r.getTargetProductCode());
        var saved = repo.save(p);

        var evt = PromotionEvent.builder()
                .type(PromotionEvent.Type.PROMOTION_SET)
                .kitchen(saved.getKitchen())
                .promotionType(saved.getType())
                .active(saved.isActive())
                .targetProductCode(saved.getTargetProductCode())
                .ts(Instant.now().toEpochMilli())
                .build();

        kafka.send(TOPIC, saved.getKitchen().name(), evt);
        return saved;
    }
}

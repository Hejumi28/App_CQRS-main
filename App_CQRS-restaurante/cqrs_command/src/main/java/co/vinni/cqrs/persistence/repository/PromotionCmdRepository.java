package co.vinni.cqrs.persistence.repository;

import co.vinni.cqrs.persistence.entity.Kitchen;
import co.vinni.cqrs.persistence.entity.PromotionCmd;
import co.vinni.cqrs.persistence.entity.PromotionType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PromotionCmdRepository extends JpaRepository<PromotionCmd, Long> {
    Optional<PromotionCmd> findByKitchenAndType(Kitchen kitchen, PromotionType type);
}

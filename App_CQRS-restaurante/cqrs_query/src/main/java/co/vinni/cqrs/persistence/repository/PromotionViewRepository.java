// co/vinni/cqrs/persistence/repository/PromotionViewRepository.java
package co.vinni.cqrs.persistence.repository;

import co.vinni.cqrs.persistence.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PromotionViewRepository extends JpaRepository<PromotionView, Long> {
    List<PromotionView> findByKitchen(Kitchen kitchen);
}

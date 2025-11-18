package co.vinni.cqrs.persistence.repository;

import co.vinni.cqrs.persistence.entity.*;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PromotionViewRepository extends MongoRepository<PromotionView, String> {
    List<PromotionView> findByKitchen(Kitchen kitchen);
}

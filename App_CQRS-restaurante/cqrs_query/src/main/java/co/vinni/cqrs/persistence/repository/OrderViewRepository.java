package co.vinni.cqrs.persistence.repository;

import co.vinni.cqrs.persistence.entity.*;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderViewRepository extends MongoRepository<OrderView, Long> {
    List<OrderView> findByKitchenAndStatus(Kitchen kitchen, OrderStatus status);
}

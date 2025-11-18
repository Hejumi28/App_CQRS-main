// co/vinni/cqrs/persistence/repository/OrderViewRepository.java
package co.vinni.cqrs.persistence.repository;

import co.vinni.cqrs.persistence.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrderViewRepository extends JpaRepository<OrderView, Long> {
    List<OrderView> findByKitchenAndStatus(Kitchen kitchen, OrderStatus status);
}

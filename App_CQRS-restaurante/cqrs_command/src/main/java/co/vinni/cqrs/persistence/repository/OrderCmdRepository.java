package co.vinni.cqrs.persistence.repository;

import co.vinni.cqrs.persistence.entity.OrderCmd;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderCmdRepository extends JpaRepository<OrderCmd, Long> { }

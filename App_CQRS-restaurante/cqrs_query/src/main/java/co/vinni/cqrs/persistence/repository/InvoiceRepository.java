// co/vinni/cqrs/persistence/repository/InvoiceRepository.java
package co.vinni.cqrs.persistence.repository;

import co.vinni.cqrs.persistence.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    Optional<Invoice> findByOrderId(Long orderId);
}

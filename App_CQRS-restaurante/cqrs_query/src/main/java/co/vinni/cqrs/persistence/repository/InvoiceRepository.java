package co.vinni.cqrs.persistence.repository;

import co.vinni.cqrs.persistence.entity.Invoice;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface InvoiceRepository extends MongoRepository<Invoice, String> {
    Optional<Invoice> findByOrderId(Long orderId);
}

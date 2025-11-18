package co.vinni.cqrs.persistence.repository;

import co.vinni.cqrs.persistence.entity.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepository extends MongoRepository<Student, Long> {
}

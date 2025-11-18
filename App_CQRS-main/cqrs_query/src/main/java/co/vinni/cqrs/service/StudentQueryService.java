package co.vinni.cqrs.service;

import co.vinni.cqrs.dto.StudentEvent;
import co.vinni.cqrs.persistence.entity.Student;
import co.vinni.cqrs.persistence.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentQueryService {

    private final StudentRepository studentRepository;

    public List<Student> getAll() {
        return this.studentRepository.findAll();
    }

    @KafkaListener(topics = "student-event-topic", groupId = "student-event-group")
    public void processStudentEvents(StudentEvent studentEvent) {

        Student student = studentEvent.getStudent();

        if (studentEvent.getEventType().equals("CreateStudent")) {
            studentRepository.save(student);
        }

        if (studentEvent.getEventType().equals("UpdateStudent")) {
            Student existing = studentRepository.findById(student.getCode()).orElseThrow();

            existing.setFirstName(student.getFirstName());
            existing.setLastName(student.getLastName());
            existing.setEmail(student.getEmail());

            studentRepository.save(existing);
        }
    }
}

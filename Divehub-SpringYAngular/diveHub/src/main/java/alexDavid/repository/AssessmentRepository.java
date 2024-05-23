package alexDavid.repository;

import alexDavid.models.Assessment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface AssessmentRepository extends JpaRepository<Assessment, Long> {
    List<Assessment> findAllByUser_Email(String email);
    List<Assessment> findAllByProductId(Long id);
    Double countAllByUser_Email(String email);
    Double countAllByProductId(Long id);
    void deleteAllByUser_Email(String email);
}

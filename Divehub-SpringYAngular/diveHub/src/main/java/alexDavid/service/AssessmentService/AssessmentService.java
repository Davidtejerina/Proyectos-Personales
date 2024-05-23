package alexDavid.service.AssessmentService;

import alexDavid.models.Assessment;
import java.util.List;


public interface AssessmentService {
    List<Assessment> getAllByUser(String email);
    List<Assessment> getAllByProduct(Long productId);
    Double getCountByProduct(Long productId);
    Double getCountByEmail(String email);
    Assessment addAssessment(Assessment assessment);
    Assessment update(Long id, Assessment assessment);
    void deleteById(Long id);
    void deleteAll(String email);
}

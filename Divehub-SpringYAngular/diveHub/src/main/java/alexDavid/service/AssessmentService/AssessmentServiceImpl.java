package alexDavid.service.AssessmentService;

import alexDavid.models.Assessment;
import alexDavid.repository.AssessmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@RequiredArgsConstructor
public class AssessmentServiceImpl implements AssessmentService{
    private final AssessmentRepository assessmentRepository;

    @Override
    public List<Assessment> getAllByUser(String email) {
        return assessmentRepository.findAllByUser_Email(email);
    }

    @Override
    public List<Assessment> getAllByProduct(Long productId) {
        return assessmentRepository.findAllByProductId(productId);
    }

    @Override
    public Double getCountByProduct(Long productId) {
        return assessmentRepository.countAllByProductId(productId);
    }

    @Override
    public Double getCountByEmail(String email) {
        return assessmentRepository.countAllByUser_Email(email);
    }

    @Override
    public Assessment addAssessment(Assessment assessment) {
        return assessmentRepository.save(assessment);
    }

    @Override
    public Assessment update(Long id, Assessment assessment) {
        Assessment newAssessment = assessmentRepository.findById(id).orElseThrow();
        newAssessment.setContent(assessment.getContent());
        newAssessment.setStars(assessment.getStars());
        return assessmentRepository.save(newAssessment);
    }

    @Override
    public void deleteById(Long id) {
        assessmentRepository.deleteById(id);
    }

    @Override
    public void deleteAll(String email) {
        assessmentRepository.deleteAllByUser_Email(email);
    }
}

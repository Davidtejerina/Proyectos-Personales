package alexDavid.mappers;

import alexDavid.dtos.AssessmentDTO.AssessmentRequestDto;
import alexDavid.dtos.AssessmentDTO.AssessmentResponseDto;
import alexDavid.models.Assessment;
import org.springframework.stereotype.Component;
import java.util.List;


@Component
public class AssessmentMapper {
    public AssessmentResponseDto toResponse(Assessment assessment){
        return new AssessmentResponseDto(
                assessment.getId(),
                assessment.getUser(),
                assessment.getContent(),
                assessment.getStars(),
                assessment.getDate(),
                assessment.getProductId()
        );
    }

    public List<AssessmentResponseDto> toResponse(List<Assessment> assessments) {
        return assessments.stream().
                map(this::toResponse).
                toList();
    }

    public Assessment toModel(AssessmentRequestDto assessmentRequestDto) {
        return new Assessment(
                null,
                assessmentRequestDto.getUser(),
                assessmentRequestDto.getContent(),
                assessmentRequestDto.getStars(),
                assessmentRequestDto.getDate(),
                assessmentRequestDto.getProductId()
        );
    }
}

package alexDavid.controller;

import alexDavid.dtos.AssessmentDTO.AssessmentRequestDto;
import alexDavid.dtos.AssessmentDTO.AssessmentResponseDto;
import alexDavid.mappers.AssessmentMapper;
import alexDavid.models.Assessment;
import alexDavid.service.AssessmentService.AssessmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/divehub/assessments")
@RequiredArgsConstructor
@CrossOrigin
public class AssessmentController {
    private final AssessmentService assessmentService;
    private final AssessmentMapper assessmentMapper;

    @GetMapping("/allByEmail/{email}")
    public ResponseEntity<List<AssessmentResponseDto>> getAssessmentsByUser(
            @PathVariable String email
    ){
        return ResponseEntity.ok(assessmentMapper.toResponse(assessmentService.getAllByUser(email)));
    }

    @GetMapping("/allByProduct/{product_id}")
    public ResponseEntity<List<AssessmentResponseDto>> getAssessmentsByProduct(
            @PathVariable Long product_id
    ){
        return ResponseEntity.ok(assessmentMapper.toResponse(assessmentService.getAllByProduct(product_id)));
    }

    @GetMapping("/countTotalByProduct/{product_id}")
    public ResponseEntity<Double> getTotalByProduct(
            @PathVariable Long product_id
    ){
        return ResponseEntity.ok(assessmentService.getCountByProduct(product_id));
    }

    @GetMapping("/countTotalByEmail/{email}")
    public ResponseEntity<Double> getTotalByUser(
            @PathVariable String email
    ){
        return ResponseEntity.ok(assessmentService.getCountByEmail(email));
    }

    @PostMapping
    public ResponseEntity<?> addAssessment(
            @RequestBody AssessmentRequestDto assessmentRequestDto
    ){
        Assessment assessment = assessmentMapper.toModel(assessmentRequestDto);
        return ResponseEntity.ok(assessmentService.addAssessment(assessment));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAssessment(
            @PathVariable Long id,
            @RequestBody AssessmentRequestDto assessmentRequestDto
    ){
        Assessment assessment = assessmentMapper.toModel(assessmentRequestDto);
        return ResponseEntity.ok(assessmentService.update(id, assessment));
    }

    @DeleteMapping("/clean/{id}")
    public ResponseEntity<?> removeAssessment(
            @PathVariable Long id
    ){
        assessmentService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/cleanAll/{email}")
    public ResponseEntity<?> removeAllAssessments(
            @PathVariable String email
    ){
        assessmentService.deleteAll(email);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
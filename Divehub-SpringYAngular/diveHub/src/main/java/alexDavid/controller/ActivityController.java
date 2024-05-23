package alexDavid.controller;

import alexDavid.dtos.ActivityDTO.ActivityResponseDto;
import alexDavid.mappers.ActivityMapper;
import alexDavid.service.ActivityService.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import java.time.Duration;
import java.util.List;


@RestController
@RequestMapping("/divehub/activities")
@RequiredArgsConstructor
@CrossOrigin
public class ActivityController {
    private final ActivityService activityService;
    private final ActivityMapper activityMapper;

    @GetMapping("/all")
    public ResponseEntity<List<ActivityResponseDto>> getAllActivities(
    ) {
        return ResponseEntity.ok(activityMapper.toResponse(activityService.findAll()));
    }

    @GetMapping("/getActivityById/{id}")
    public ResponseEntity<ActivityResponseDto> getActivityById(
        @PathVariable Long id
    ) {
        return ResponseEntity.ok(activityMapper.toResponse(activityService.findById(id)));
    }

    @GetMapping("/tag/{tag}")
    public ResponseEntity<List<ActivityResponseDto>> getActivitiesByTag(
            @PathVariable String tag
    ) {
        return ResponseEntity.ok(activityMapper.toResponse(activityService.findProductsByTagIgnoreCase(tag)));
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<ActivityResponseDto>> getActivitiesByCategory(
            @PathVariable Integer category
    ) {
        return ResponseEntity.ok(activityMapper.toResponse(activityService.findProductsByCategory(category)));
    }

    @GetMapping("/getActivityByName/{name}")
    public ResponseEntity<List<ActivityResponseDto>> getActivitiesByName(
            @PathVariable String name
    ) {
        return ResponseEntity.ok(activityMapper.toResponse(activityService.findByName(name)));
    }

    @GetMapping("/available")
    public ResponseEntity<List<ActivityResponseDto>> getAllAvailableActivities(
    ){
        return ResponseEntity.ok(activityMapper.toResponse(activityService.findByAvailable()));
    }

    @GetMapping("/available_spaces/{id}")
    public ResponseEntity<Integer> getAllAvailable_spacesByActivityId(
            @PathVariable Long id
    ){
        return ResponseEntity.ok(activityService.findById(id).getAvailable_spaces());
    }

    @GetMapping("/remaining-time/{id}")
    public ResponseEntity<Duration> getRemainingTimeByActivityId(
            @PathVariable Long id
    ){
        return ResponseEntity.ok(activityService.getRemainingTime(id)); //en horas
    }

    @GetMapping("/updateTimeEnds/{id}/{date}")
    public ResponseEntity<ActivityResponseDto> updateEndTimeByActivityId(
            @PathVariable Long id,
            @PathVariable String date
    ){
        return ResponseEntity.ok(activityMapper.toResponse(activityService.updateEndTime(id, date)));
    }

    @GetMapping("/updateTimeStarts/{id}/{date}")
    public ResponseEntity<ActivityResponseDto> updateStartTimeByActivityId(
            @PathVariable Long id,
            @PathVariable String date
    ){
        System.out.println(date);
        return ResponseEntity.ok(activityMapper.toResponse(activityService.updateStartTime(id, date)));
    }

    @GetMapping("/updateAvailable_spaces/{id}")
    public ResponseEntity<?> updateAvailable_spaces(
            @PathVariable Long id
    ){
        activityService.updateAvailable_spaces(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/isActivityAvailableForUser/{id}/{email}")
    public ResponseEntity<Boolean> isActivityAvailableForUser(
            @PathVariable Long id,
            @PathVariable String email
    ){
        return ResponseEntity.ok(activityService.isActivityAvailableForUser(id, email));
    }

    @GetMapping("/isAvailableByLevel/{id}/{email}")
    public ResponseEntity<Boolean>isAvailableByLevel(
            @PathVariable Long id,
            @PathVariable String email
    ){
        return ResponseEntity.ok(activityService.IsAvailableByLevel(id, email));
    }
}

package alexDavid.service.ActivityService;

import alexDavid.models.Activity;
import alexDavid.models.Category;
import alexDavid.models.Detail;
import alexDavid.repository.ActivityRepository;
import alexDavid.repository.DetailRepository;
import alexDavid.repository.UserDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ActivityServiceImpl implements ActivityService {
    private final ActivityRepository activityRepository;
    private final DetailRepository detailRepository;
    private final UserDetailsRepository userDetailsRepository;

    @Override
    public List<Activity> findAll() {
        return activityRepository.findAll();
    }

    @Override
    public Activity findById(Long id) {
        return activityRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Activity> findProductsByTagIgnoreCase(String tag) {
        return activityRepository.findProductsByTagIgnoreCase(tag);
    }

    @Override
    public List<Activity> findProductsByCategory(Integer categoryIndex) {
        Category[] categories = Category.values();
        if (categoryIndex >= 0 && categoryIndex < categories.length) {
            Category category = categories[categoryIndex];
            return activityRepository.findByCategory(category);
        }
        else throw new IllegalArgumentException("Índice de categoría inválido: " + categoryIndex);
    }

    @Override
    public List<Activity> findByName(String name){
        return activityRepository.findByNameContainsIgnoreCase(name);
    }

    @Override
    public Activity updateEndTime(Long id, String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");
        LocalDateTime newDateTime = LocalDateTime.parse(date, formatter);
        Activity a = this.findById(id);
        a.setTime_ends(newDateTime);
        return activityRepository.save(a);
    }

    @Override
    public Activity updateStartTime(Long id, String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");
        LocalDateTime newDateTime = LocalDateTime.parse(date, formatter);
        Activity a = this.findById(id);
        a.setTime_starts(newDateTime);
        return activityRepository.save(a);
    }

    @Override
    public void updateAvailable_spaces(Long id) {
        Activity a = this.findById(id);
        a.setAvailable_spaces(a.getAvailable_spaces()+1);
        activityRepository.save(a);
    }

    @Override
    public Boolean isActivityAvailableForUser(Long activityId, String userEmail) {
        Activity activity = activityRepository.findById(activityId).orElseThrow();
        List<Detail> userDetails = detailRepository.findAllByOrder_User_EmailAndOrderDateAfter(userEmail, LocalDateTime.now().minusDays(30));

        boolean activityBought = userDetails.stream()
                .anyMatch(detail -> detail.getProductId().equals(activity.getId()));

        if (!activityBought) return true;

        LocalDateTime now = LocalDateTime.now();
        return !activity.getTime_ends().isAfter(now);
    }

    @Override
    public Boolean IsAvailableByLevel(Long activityId, String email) {
        return (userDetailsRepository.findByEmail(email).getLevel()).compareTo(activityRepository.findById(activityId).orElseThrow().getLevel_required()) >= 0;
    }

    @Override
    public Activity save(Activity activity) {
       return activityRepository.save(activity);
    }

    @Override
    public List<Activity> findByAvailable() {
        return activityRepository.findAllByAvailable(true);
    }

    @Override
    public Duration getRemainingTime(Long id){
        Activity activity = activityRepository.findById(id).orElseThrow();
        return Duration.between(activity.getTime_starts(), activity.getTime_ends());
    }
}

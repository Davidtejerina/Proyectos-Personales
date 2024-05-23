package alexDavid.service.ActivityService;

import alexDavid.models.Activity;
import java.time.Duration;
import java.util.List;


public interface ActivityService {
    List<Activity> findAll();
    Activity findById(Long id);
    List<Activity> findProductsByTagIgnoreCase(String tag);
    List<Activity> findProductsByCategory(Integer category);
    Activity save(Activity activity);
    List<Activity> findByAvailable();
    Duration getRemainingTime(Long id);
    List<Activity> findByName(String name);
    Activity updateEndTime(Long id, String date);
    Activity updateStartTime(Long id, String date);
    void updateAvailable_spaces(Long id);
    Boolean isActivityAvailableForUser(Long id, String email);
    Boolean IsAvailableByLevel(Long id, String email);
}

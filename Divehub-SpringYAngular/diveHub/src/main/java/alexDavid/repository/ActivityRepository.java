package alexDavid.repository;

import alexDavid.models.Activity;
import alexDavid.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface ActivityRepository extends JpaRepository<Activity, Long> {
    List<Activity> findProductsByTagIgnoreCase(String tag);
    List<Activity> findAllByAvailable(Boolean available);
    List<Activity> findByNameContainsIgnoreCase(String name);
    List<Activity> findByCategory(Category category);
}

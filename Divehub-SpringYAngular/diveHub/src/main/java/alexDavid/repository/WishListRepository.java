package alexDavid.repository;

import alexDavid.models.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;


public interface WishListRepository extends JpaRepository<WishList, Long> {
    List<WishList> findByUser_Email(String userEmail);
    @Query("SELECT COUNT(w) > 0 FROM WishList w WHERE (w.item.id = :itemId OR w.activity.id = :activityId) AND w.user.email = :email")
    Boolean isItemOrActivityLiked(Long itemId, Long activityId, String email);
    void deleteByUser_Email(String email);
    void deleteByItem_IdAndUser_Email(Long itemId, String email);
    void deleteByActivity_IdAndUser_Email(Long activityId, String email);
}

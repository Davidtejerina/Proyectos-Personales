package alexDavid.repository;

import alexDavid.models.Detail;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;


public interface DetailRepository extends JpaRepository<Detail, Long> {
    List<Detail> findByOrder_User_Email(String userEmail);
    List<Detail> findByOrder_Id(Long id);
    void deleteByOrder_User_Email(String email);
    List<Detail> findAllByOrder_User_EmailAndOrderDateAfter(String email, LocalDateTime thirtyDaysAgo);
}

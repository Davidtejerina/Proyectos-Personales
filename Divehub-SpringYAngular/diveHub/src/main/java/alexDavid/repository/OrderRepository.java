package alexDavid.repository;

import alexDavid.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser_Email(String userEmail);
    void deleteByUser_Email(String email);
}

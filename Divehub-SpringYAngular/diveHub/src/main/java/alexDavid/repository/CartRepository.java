package alexDavid.repository;

import alexDavid.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findByUser_Email(String userEmail);
    Cart findByUser_EmailAndProductId(String userEmail, Long productId);
    void deleteByUser_Email(String email);
    void deleteByUser_EmailAndProductId(String email, Long productId);
    Long countByUser_Email(String email);
}

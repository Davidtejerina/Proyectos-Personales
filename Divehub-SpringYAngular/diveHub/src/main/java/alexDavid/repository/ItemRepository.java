package alexDavid.repository;

import alexDavid.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;


public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findProductsByTagIgnoreCase(String tag);
    List<Item> findByNameContainsIgnoreCase(String name);
    @Query("SELECT i FROM Item i ORDER BY i.final_price DESC")
    List<Item> findAllOrderByFinal_price();
    @Query("SELECT i FROM Item i ORDER BY i.final_price ASC")
    List<Item> findAllOrderByFinal_priceAsc();
}

package alexDavid.service.ItemService;

import alexDavid.models.Item;
import java.util.List;


public interface ItemService {
    Item findById(Long id);
    List<Item> findAll();
    Item save(Item item);
    List<Item> findProductsByTagIgnoreCase(String tag);
    List<Item> findByNameContainsIgnoreCase(String name);
    List<Item> findAllByOrderByFinal_priceDesc();
    List<Item> findAllByOrderByFinal_priceAsc();
    void deleteProductById(Long id);
    Item update (Long id, Item item);
    Boolean hasStock(Long id);

}

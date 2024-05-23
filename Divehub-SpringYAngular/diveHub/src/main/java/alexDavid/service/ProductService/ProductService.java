package alexDavid.service.ProductService;

import alexDavid.models.Product;
import java.util.List;


public interface ProductService {
    Product findById(Long id);
    List<Product> findByCategory(Integer category);
    List<Product> findAll();
    List<Product> findProductsByTagIgnoreCase(String tag);
    List<Product> findByNameContainsIgnoreCase(String name);
    Boolean getIsItem(Long id);
}

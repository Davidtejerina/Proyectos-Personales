package alexDavid.service.ProductService;

import alexDavid.models.Category;
import alexDavid.models.Product;
import alexDavid.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Product> findByCategory(Integer categoryIndex) {
        Category[] categories = Category.values();
        if (categoryIndex >= 0 && categoryIndex < categories.length) {
            Category category = categories[categoryIndex];
            return productRepository.findProductsByCategory(category);
        }
        else throw new IllegalArgumentException("Índice de categoría inválido: " + categoryIndex);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> findProductsByTagIgnoreCase(String tag) {
        return productRepository.findProductsByTagIgnoreCase(tag);
    }

    @Override
    public List<Product> findByNameContainsIgnoreCase(String name) {
        return productRepository.findByNameContainsIgnoreCase(name);
    }

    @Override
    public Boolean getIsItem(Long id) {
        return this.findById(id).getCategory().toString().equals("PRODUCT");
    }
}

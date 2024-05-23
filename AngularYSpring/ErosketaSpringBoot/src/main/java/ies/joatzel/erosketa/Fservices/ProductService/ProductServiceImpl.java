package ies.joatzel.erosketa.Fservices.ProductService;

import ies.joatzel.erosketa.Amodels.*;
import ies.joatzel.erosketa.Crepository.CategoryRepository;
import ies.joatzel.erosketa.Crepository.ProductRepository;
import ies.joatzel.erosketa.exception.Product.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
import net.datafaker.Faker;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAllOrderByCategoryId();    //PARA QUE ME ORDENE LOS PRODUCTS POR CATEGORIA
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(
                () -> new ProductNotFoundException("Could not find product with id: " + id)
        );
    }

    @Override
    public List<Product> findAllByName(String name) {
        return productRepository.findAllByNameContainsIgnoreCase(name);
    }

    @Override
    public List<Product> findByCategoryId(Long id) {
        return productRepository.findByCategoryId(id);
    }


    @Override
    public Product save(Product product) {
        Category miCategory = null;
        if (product.getCategory() != null) {
            miCategory = categoryRepository.findById(product.getCategory().getId()).orElseThrow();
        }

        product.setCategory(miCategory);
        return productRepository.save(product);
    }

    @Override
    public Product update(Long id, Product product) {
        var updated = this.findById(id);
        Category miCategory = null;

        if (product.getCategory() != null) {
            miCategory = categoryRepository.findById(product.getCategory().getId()).orElseThrow();
        }

        updated.setName(product.getName());
        updated.setDescription(product.getDescription());
        updated.setPrice(product.getPrice());
        updated.setStock(product.getStock());
        updated.setImage(product.getImage());
        updated.setCategory(miCategory);

        return productRepository.save(updated);
    }

    @Override
    public void deleteById(Long id) {
        this.findById(id);
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> generateProductsForCategory(Long categoryId, int numberOfProducts) {
        Category category = categoryRepository.findById(categoryId).orElseThrow();
        List<Product> generatedProducts = new ArrayList<>();
        for (int i = 0; i < numberOfProducts; i++) generatedProducts.add(this.save(generateRandomProduct(category)));

        return generatedProducts;
    }

    private Product generateRandomProduct(Category category) {
        Faker faker = new Faker();
        String dish = faker.food().ingredient();

        return new Product(
            null,
            dish,
            faker.lorem().sentence(),
            faker.number().randomDouble(2, 2, 25),
            faker.number().numberBetween(-10, 20),
            "https://source.unsplash.com/300x200/?" + dish,
            category
        );
    }
}
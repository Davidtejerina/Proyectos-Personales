package ies.joatzel.erosketa.Fservices.ProductService;

import ies.joatzel.erosketa.Amodels.*;
import org.springframework.lang.Nullable;

import java.util.List;

public interface ProductService {
    java.util.List<Product> findAll();
    Product findById(Long id);
    java.util.List<Product> findAllByName(String name);
    java.util.List<Product> findByCategoryId(Long id);
    Product save(Product product);
    Product update(Long id, Product product);
    void deleteById(Long id);
    List<Product> generateProductsForCategory(Long categoryId, int numberOfProducts);
}
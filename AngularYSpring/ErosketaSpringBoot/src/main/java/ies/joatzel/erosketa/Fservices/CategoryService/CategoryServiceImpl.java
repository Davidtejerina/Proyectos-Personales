package ies.joatzel.erosketa.Fservices.CategoryService;

import ies.joatzel.erosketa.Amodels.Category;
import ies.joatzel.erosketa.Amodels.Product;
import ies.joatzel.erosketa.Amodels.WishList;
import ies.joatzel.erosketa.Crepository.CartRepository;
import ies.joatzel.erosketa.Crepository.CategoryRepository;
import ies.joatzel.erosketa.Crepository.ProductRepository;
import ies.joatzel.erosketa.Crepository.WishListRepository;
import ies.joatzel.erosketa.exception.Category.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final WishListRepository wishListRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id).orElseThrow(
                () -> new CategoryNotFoundException("Could not find category with id: " + id)
        );
    }

    @Override
    public List<Category> findAllByName(String name) {
        return categoryRepository.findByNameContainsIgnoreCase(name);
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category update(Long id, Category category) {
        var updated = this.findById(id);
        updated.setName(category.getName());
        updated.setDescription(category.getDescription());
        updated.setColor(category.getColor());
        updated.setImage(category.getImage());

        return categoryRepository.save(updated);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        Category categoryToDelete = categoryRepository.findById(categoryId).orElse(null);

        if (categoryToDelete != null) {
            List<Product> productsToDelete = productRepository.findByCategoryId(categoryToDelete.getId());
            wishListRepository.deleteByProduct_Category_Id(categoryId);
            cartRepository.deleteByProduct_CategoryId(categoryId);
            productRepository.deleteAll(productsToDelete);
            categoryRepository.delete(categoryToDelete);
        }
    }
}
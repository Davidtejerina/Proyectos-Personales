package ies.joatzel.erosketa.Fservices.CategoryService;

import ies.joatzel.erosketa.Amodels.Category;


public interface CategoryService {
    java.util.List<Category> findAll();
    Category findById(Long id);
    java.util.List<Category> findAllByName(String name);
    Category save(Category category);
    Category update(Long id, Category category);
    void deleteCategory(Long id);
}
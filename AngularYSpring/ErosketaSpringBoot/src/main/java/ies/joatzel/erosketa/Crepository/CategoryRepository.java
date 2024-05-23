package ies.joatzel.erosketa.Crepository;

import ies.joatzel.erosketa.Amodels.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Interfaz para los repositorios de Raquetas con las operaciones CRUD
 */

public interface CategoryRepository extends JpaRepository<Category, Long> {
    java.util.List<Category> findByNameContainsIgnoreCase(String name);
}
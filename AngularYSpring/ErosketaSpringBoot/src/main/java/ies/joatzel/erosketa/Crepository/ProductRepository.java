package ies.joatzel.erosketa.Crepository;

import ies.joatzel.erosketa.Amodels.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Interfaz para los repositorios de Raquetas con las operaciones CRUD
 */


public interface ProductRepository extends JpaRepository<Product, Long> {
    java.util.List<Product> findAllByNameContainsIgnoreCase(String name);
    List<Product> findByCategoryId(Long id);
    @Query("SELECT p FROM Product p ORDER BY p.category.id")
    java.util.List<Product> findAllOrderByCategoryId();
}
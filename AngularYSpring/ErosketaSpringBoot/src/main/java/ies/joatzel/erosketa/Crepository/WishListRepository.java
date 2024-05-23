package ies.joatzel.erosketa.Crepository;

import ies.joatzel.erosketa.Amodels.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WishListRepository extends JpaRepository<WishList, Long> {
    List<WishList> findByUser_Email(String userEmail);
    @Query("SELECT COUNT(w) > 0 FROM WishList w WHERE w.product.id = :productId AND w.user.email = :email")
    Boolean isProductLiked(Long productId, String email);
    void deleteByUser_Email(String email);
    void deleteByProduct_Category_Id(Long categoryId);
    void deleteByProduct_IdAndUser_Email(Long productId, String email);
}

package ies.joatzel.erosketa.Fservices.WishList;

import ies.joatzel.erosketa.Amodels.WishList;


public interface WishListService {
    java.util.List<WishList> getListByUser(String email);
    void cleanWishList(String email);
    void removeProduct(Long productId, String email);
    void addProduct(WishList wishList);
    Boolean isProductLiked(String email, Long product);
}















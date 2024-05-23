package alexDavid.service.CartService;

import alexDavid.models.Cart;


public interface CartService {
    java.util.List<Cart> getListByUser(String email);
    void cleanCart(String email);
    void addProduct(Cart cart);
    Long getCountByClient(String email);
    double getTotalPriceByEmail(String email);
    void updateProductQuantity(String user_email, Long productId, Integer quantity);
    boolean isProductInCart(Cart cart);
    boolean isProductInCart(String userEmail, Long productId);
    void removeProduct(String email, Long productId);
}

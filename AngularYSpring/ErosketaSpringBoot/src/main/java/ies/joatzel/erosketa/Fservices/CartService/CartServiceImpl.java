package ies.joatzel.erosketa.Fservices.CartService;

import ies.joatzel.erosketa.Amodels.Cart;
import ies.joatzel.erosketa.Crepository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CartServiceImpl implements CartService{
    private final CartRepository cartRepository;

    @Override
    public List<Cart> getListByUser(String email){
        return this.cartRepository.findByUser_Email(email);
    }

    @Override
    public void cleanCart(String email){
        this.cartRepository.deleteByUser_Email(email);
    }

    @Override
    public void removeProduct(Long productId){
        this.cartRepository.deleteByProduct_Id(productId);
    }

    @Override
    public void addProduct(Cart cart){
        this.cartRepository.save(cart);
    }

    @Override
    public Long getCountByClient(String email){
        return this.cartRepository.countByUser_Email(email);
    }

    @Override
    public double getTotalPriceByEmail(String email) {
        List<Cart> cartItems = getListByUser(email);
        return cartItems.stream()
                .mapToDouble(cart -> cart.getProduct().getPrice() * cart.getAmount())
                .sum();
    }

    @Override
    public void updateProductQuantity(String user_email, Long productId, int quantity) {
        Cart cart = cartRepository.findByUser_EmailAndProduct_Id(user_email, productId);
        cart.setAmount(quantity);
        cartRepository.save(cart);
    }

    @Override
    public boolean isProductInCart(Cart cart) {
        return cartRepository.findByUser_EmailAndProduct_Id(cart.getUser().getEmail(), cart.getProduct().getId()) != null;
    }


}















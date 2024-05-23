package ies.joatzel.erosketa.Emapper;

import ies.joatzel.erosketa.Amodels.Cart;
import ies.joatzel.erosketa.Dto.CartDto.CartRequestDto;
import org.springframework.stereotype.Component;


@Component
public class CartMapper {
    public Cart toModel(CartRequestDto cartDTO) {
        return new Cart(
                null,
                cartDTO.getUser(),
                cartDTO.getProduct(),
                cartDTO.getAmount()
        );
    }
}

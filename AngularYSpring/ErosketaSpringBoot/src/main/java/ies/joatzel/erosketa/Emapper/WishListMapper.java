package ies.joatzel.erosketa.Emapper;

import ies.joatzel.erosketa.Amodels.Cart;
import ies.joatzel.erosketa.Amodels.WishList;
import ies.joatzel.erosketa.Dto.CartDto.CartRequestDto;
import ies.joatzel.erosketa.Dto.WishListDto.WishListRequestDto;
import org.springframework.stereotype.Component;


@Component
public class WishListMapper {
    public WishList toModel(WishListRequestDto wishListDTO) {
        return new WishList(
                null,
                wishListDTO.getUser(),
                wishListDTO.getProduct()
        );
    }
}

package ies.joatzel.erosketa.Dto.WishListDto;

import ies.joatzel.erosketa.Amodels.Product;
import ies.joatzel.erosketa.Amodels.User.User;
import lombok.Data;

@Data
public class WishListRequestDto {
    private final User user;
    private final Product product;
}

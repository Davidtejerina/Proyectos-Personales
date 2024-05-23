package ies.joatzel.erosketa.Dto.WishListDto;

import ies.joatzel.erosketa.Amodels.Product;
import ies.joatzel.erosketa.Amodels.User.User;
import lombok.Data;

@Data
public class WishListResponseDto {
    private final Long id;
    private final User user;
    private final Product product;
}

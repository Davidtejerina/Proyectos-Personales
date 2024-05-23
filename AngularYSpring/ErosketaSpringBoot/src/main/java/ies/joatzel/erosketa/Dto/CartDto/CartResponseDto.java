package ies.joatzel.erosketa.Dto.CartDto;


import ies.joatzel.erosketa.Amodels.Product;
import ies.joatzel.erosketa.Amodels.User.User;
import lombok.*;

@Data
public class CartResponseDto {
    private final Long id;
    private final User user;
    private final Product product;
    private final int amount;
}

package alexDavid.dtos.CartDTO;

import alexDavid.models.User.User;
import lombok.Data;


@Data
public class CartRequestDto {
    private final User user;
    private final Long productId;
    private final Integer quantity;
}

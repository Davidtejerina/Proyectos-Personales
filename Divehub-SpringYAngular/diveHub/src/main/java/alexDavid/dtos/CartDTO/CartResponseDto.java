package alexDavid.dtos.CartDTO;

import alexDavid.models.User.User;
import lombok.Data;


@Data
public class CartResponseDto {
    private final Long id;
    private final User user;
    private final Long ProductId;
    private final Integer quantity;
}

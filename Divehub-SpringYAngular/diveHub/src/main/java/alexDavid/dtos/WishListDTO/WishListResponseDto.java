package alexDavid.dtos.WishListDTO;

import alexDavid.models.Activity;
import alexDavid.models.Item;
import alexDavid.models.User.User;
import lombok.Data;


@Data
public class WishListResponseDto {
    private final Long id;
    private final User user;
    private final Item item;
    private final Activity activity;
}

package alexDavid.dtos.OrderDTO;

import alexDavid.models.User.User;
import lombok.Data;
import java.time.LocalDateTime;


@Data
public class OrderResponseDto {
    private final Long id;
    private final User user;
    private final double total;
    private final String address;
    private final LocalDateTime date;
}

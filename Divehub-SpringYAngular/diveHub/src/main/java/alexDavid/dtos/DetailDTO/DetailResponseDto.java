package alexDavid.dtos.DetailDTO;


import alexDavid.models.Order;
import lombok.Data;


@Data
public class DetailResponseDto {
    private final Long id;
    private final Order order;
    private final Long productId;
    private final Integer quantity;
}

package alexDavid.dtos.ItemDTO;

import lombok.Data;
import alexDavid.models.Category;


@Data
public class ItemResponseDto {
    private final Long id;
    private final String name;
    private final String description;
    private final Double starting_price;
    private final Double final_price;
    private final String image;
    private final Double weight;
    private final Category category;
    private final String tag;
    private final Integer stock;
}

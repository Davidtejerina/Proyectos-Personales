package alexDavid.mappers;

import alexDavid.dtos.ItemDTO.ItemRequestDto;
import alexDavid.dtos.ItemDTO.ItemResponseDto;
import alexDavid.models.Item;
import org.springframework.stereotype.Component;
import java.util.List;


@Component
public class ItemMapper {
    public ItemResponseDto toResponse(Item item) {
        return new ItemResponseDto(
                item.getId(),
                item.getName(),
                item.getDescription(),
                item.getStarting_price(),
                item.getFinal_price(),
                item.getImage(),
                item.getWeight(),
                item.getCategory(),
                item.getTag(),
                item.getStock()
        );
    }

    public List<ItemResponseDto> toResponse(List<Item> items) {
        return items.stream().
                map(this::toResponse).
                toList();
    }

    public Item toModel(ItemRequestDto itemRequestDto) {
        return new Item(
                null,
                itemRequestDto.getName(),
                itemRequestDto.getDescription(),
                itemRequestDto.getStarting_price(),
                itemRequestDto.getFinal_price(),
                itemRequestDto.getImage(),
                itemRequestDto.getWeight(),
                itemRequestDto.getCategory(),
                itemRequestDto.getTag(),
                itemRequestDto.getStock()
        );
    }
}

package alexDavid.mappers;

import alexDavid.dtos.DetailDTO.DetailRequestDto;
import alexDavid.dtos.DetailDTO.DetailResponseDto;
import alexDavid.models.Detail;
import org.springframework.stereotype.Component;


@Component
public class DetailMapper {
    public DetailResponseDto toResponse(Detail detail) {
        return new DetailResponseDto(
                detail.getId(),
                detail.getOrder(),
                detail.getProductId(),
                detail.getQuantity()
        );
    }

    public Detail toModel(DetailRequestDto detailDTO) {
        return new Detail(
                null,
                detailDTO.getOrder(),
                detailDTO.getProductId(),
                detailDTO.getQuantity()
        );
    }
}

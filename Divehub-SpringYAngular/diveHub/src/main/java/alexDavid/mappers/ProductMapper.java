package alexDavid.mappers;

import alexDavid.dtos.ProductDTO.ProductResponseDto;
import alexDavid.models.Product;
import org.springframework.stereotype.Component;
import java.util.List;


@Component
public class ProductMapper {
    public ProductResponseDto toResponse(Product product){
        return new ProductResponseDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getStarting_price(),
                product.getFinal_price(),
                product.getImage(),
                product.getCategory(),
                product.getTag()
        );
    }

    public List<ProductResponseDto> toResponse(List<Product> products) {
        return products.stream().
                map(this::toResponse).
                toList();
    }
}

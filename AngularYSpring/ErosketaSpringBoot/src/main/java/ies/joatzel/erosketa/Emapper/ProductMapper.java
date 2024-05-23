package ies.joatzel.erosketa.Emapper;

import ies.joatzel.erosketa.Amodels.Product;
import ies.joatzel.erosketa.Dto.ProductDto.ProductRequestDto;
import ies.joatzel.erosketa.Dto.ProductDto.ProductResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductMapper {
    private final CategoryMapper categoryMapper;

    public ProductResponseDto toResponse(Product product) {
        return new ProductResponseDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock(),
                product.getImage(),
                product.getCategory() != null ? categoryMapper.toResponse(product.getCategory()) : null
        );
    }

    public java.util.List<ProductResponseDto> toResponse(java.util.List<Product> product) {
        return product.stream() .map(this::toResponse) .toList();
    }

    public Product toModel(ProductRequestDto productDTO) {
        return new Product(
                null,
                productDTO.getName(),
                productDTO.getDescription(),
                productDTO.getPrice(),
                productDTO.getStock(),
                productDTO.getImage(),
                productDTO.getCategoryId() != null ? categoryMapper.toModelfromRequestDto(productDTO.getCategoryId()) : null
        );
    }
}
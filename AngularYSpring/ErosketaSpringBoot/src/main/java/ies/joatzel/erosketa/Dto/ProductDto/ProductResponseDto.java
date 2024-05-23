package ies.joatzel.erosketa.Dto.ProductDto;

import ies.joatzel.erosketa.Dto.CategoryDto.CategoryResponseDto;
import lombok.*;

@Data
public class ProductResponseDto {
    private final Long id;
    private final String name;
    private final String description;
    private final Double price;
    private final Integer stock;
    private final String image;
    private final CategoryResponseDto category;
}
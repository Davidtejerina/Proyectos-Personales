package ies.joatzel.erosketa.Dto.ProductDto;

import lombok.*;

@Data
public class ProductRequestDto {
    private final String name;
    private final String description;
    private final Double price;
    private final Integer stock;
    private final String image;
    private final Long categoryId;
}
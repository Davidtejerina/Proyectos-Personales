package ies.joatzel.erosketa.Dto.CategoryDto;

import lombok.*;

@Data
public class CategoryResponseDto {
    private final Long id;
    private final String name;
    private final String description;
    private final String color;
    private final String image;
}

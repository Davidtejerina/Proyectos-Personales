package ies.joatzel.erosketa.Dto.CategoryDto;

import lombok.*;

@Data
public class CategoryRequestDto {
    private final String name;
    private final String description;
    private final String color;
    private final String image;
}


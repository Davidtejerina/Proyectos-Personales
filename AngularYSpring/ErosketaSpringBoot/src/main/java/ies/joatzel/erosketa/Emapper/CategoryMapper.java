package ies.joatzel.erosketa.Emapper;

import ies.joatzel.erosketa.Dto.CategoryDto.CategoryResponseDto;
import ies.joatzel.erosketa.Dto.CategoryDto.CategoryRequestDto;
import ies.joatzel.erosketa.Amodels.Category;
import org.springframework.stereotype.Component;


@Component
public class CategoryMapper {
    public CategoryResponseDto toResponse(Category category) {
        return new CategoryResponseDto(
                category.getId(),
                category.getName(),
                category.getDescription(),
                category.getColor(),
                category.getImage()
        );
    }

    public java.util.List<CategoryResponseDto> toResponse(java.util.List<Category> category) {
        return category.stream() .map(this::toResponse) .toList();
    }

    public Category toModel(CategoryRequestDto categoriaDTO) {
        return new Category(
                null,
                categoriaDTO.getName(),
                categoriaDTO.getDescription(),
                categoriaDTO.getColor(),
                categoriaDTO.getImage()
        );
    }

    public Category toModelfromRequestDto(Long categoryId) {
        return new Category(
                categoryId,
                null,
                null,
                null,
                null
        );
    }
}
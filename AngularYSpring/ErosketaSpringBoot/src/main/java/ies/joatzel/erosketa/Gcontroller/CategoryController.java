package ies.joatzel.erosketa.Gcontroller;

import ies.joatzel.erosketa.Dto.CategoryDto.*;
import ies.joatzel.erosketa.Emapper.CategoryMapper;
import ies.joatzel.erosketa.Fservices.CategoryService.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.*;


/**
 * Controlador de Categorias
 * Aquí se implementan los métodos de la API REST
 * Es un controlador REST, por lo que se le indica con la anotación @RestController
 * El path base de la API REST es /api/categories y se le indica con la anotación @RequestMapping
 */


@RestController
@RequestMapping("/api/categories")
@CrossOrigin  // PERMITE EL INTERCAMBIO ENTRE BACKEND Y FRONTEND PUERTO DE ANGULAR
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @GetMapping("/all")
    public ResponseEntity<List<CategoryResponseDto>> getAllCategories(
            @RequestParam(required = false) String name
    ) {
        if(name!=null && !name.isEmpty()) return ResponseEntity.ok(categoryMapper.toResponse(categoryService.findAllByName(name)));
        return ResponseEntity.ok(categoryMapper.toResponse(categoryService.findAll()));
    }


    @GetMapping("/getBy/{id}")
    public ResponseEntity<CategoryResponseDto> getCategoryById(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(categoryMapper.toResponse(categoryService.findById(id)));
    }


    @PostMapping("/create")
    public ResponseEntity<CategoryResponseDto> postCategory(
            @RequestBody CategoryRequestDto category
    ) {
        return ResponseEntity.ok(categoryMapper.toResponse(categoryService.save(categoryMapper.toModel(category))));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<CategoryResponseDto> putCategory(
            @PathVariable Long id,
            @RequestBody CategoryRequestDto category
    ) {
        return ResponseEntity.ok(categoryMapper.toResponse(categoryService.update(id, categoryMapper.toModel(category))));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CategoryResponseDto> deleteCategoryById(
            @PathVariable Long id
    ) {
        var categoryDBToDelete = categoryService.findById(id);
        if (categoryDBToDelete==null) return ResponseEntity.notFound().build();

        categoryService.deleteCategory(id);
        return ResponseEntity.ok(categoryMapper.toResponse(categoryDBToDelete));
    }
}
package ies.joatzel.erosketa.Gcontroller;

import ies.joatzel.erosketa.Amodels.Product;
import ies.joatzel.erosketa.Dto.ProductDto.*;
import ies.joatzel.erosketa.Emapper.ProductMapper;
import ies.joatzel.erosketa.Fservices.ProductService.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.*;


/**
 * Controlador de Productos
 * Aquí se implementan los métodos de la API REST
 * Es un controlador REST, por lo que se le indica con la anotación @RestController
 * El path base de la API REST es /api/products y se le indica con la anotación @RequestMapping
 */


@RestController
@RequestMapping("/api/products")
@CrossOrigin                            // PERMITE EL INTERCAMBIO ENTRE BACKEND Y FRONTEND PUERTO DE ANGULAR
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductMapper productMapper;

    @GetMapping("/all")
    public ResponseEntity<List<ProductResponseDto>> getAllProducts(
            @RequestParam(required = false) String name
    ){
        if(name!=null && !name.isEmpty()) return ResponseEntity.ok(productMapper.toResponse(productService.findAllByName(name)));

        return ResponseEntity.ok(productMapper.toResponse(productService.findAll()));
    }

    @GetMapping("/getBy/{id}")
    public ResponseEntity<ProductResponseDto> getProductById(
            @PathVariable Long id
    ){
        return ResponseEntity.ok(productMapper.toResponse(productService.findById(id)));
    }


    @GetMapping("/category/{id}")
    public ResponseEntity<List<ProductResponseDto>> getProductByCategoryId(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(productMapper.toResponse(productService.findByCategoryId(id)));
    }


    @GetMapping("/generate-products/{categoryId}")
    public ResponseEntity<List<ProductResponseDto>> generateProductsForCategory(
            @PathVariable Long categoryId
    ) {
        List<Product> generatedProducts = productService.generateProductsForCategory(categoryId, 5);
        return ResponseEntity.ok(productMapper.toResponse(generatedProducts));
    }


    @PostMapping("/create")
    public ResponseEntity<ProductResponseDto> postProduct(
            @RequestBody ProductRequestDto product
    ) {
        return ResponseEntity.ok(productMapper.toResponse(productService.save(productMapper.toModel(product))));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<ProductResponseDto> putProduct(
            @PathVariable Long id,
            @RequestBody ProductRequestDto product
    ) {
        return ResponseEntity.ok(productMapper.toResponse(productService.update(id, productMapper.toModel(product))));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ProductResponseDto> deleteProductById(
            @PathVariable Long id
    ) {
        var productDBToDelete = productService.findById(id);
        if (productDBToDelete==null) return ResponseEntity.notFound().build();

        productService.deleteById(id);
        return ResponseEntity.ok(productMapper.toResponse(productDBToDelete));
    }
}
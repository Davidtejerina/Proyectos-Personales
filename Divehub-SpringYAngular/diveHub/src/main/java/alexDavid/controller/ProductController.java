package alexDavid.controller;

import alexDavid.dtos.ProductDTO.ProductResponseDto;
import alexDavid.mappers.ProductMapper;
import alexDavid.service.ProductService.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/divehub/products")
@RequiredArgsConstructor
@CrossOrigin
public class ProductController {
    private final ProductService productService;
    private final ProductMapper productMapper;

    @GetMapping("/all")
    public ResponseEntity<List<ProductResponseDto>> getAllProducts(
    ) {
        return ResponseEntity.ok(productMapper.toResponse(productService.findAll()));
    }

    @GetMapping("/getProductById/{id}")
    public ResponseEntity<ProductResponseDto> getProductById(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(productMapper.toResponse(productService.findById(id)));
    }

    @GetMapping("/tag/{tag}")
    public ResponseEntity<List<ProductResponseDto>> getProductsByTag(
            @PathVariable String tag
    ) {
        return ResponseEntity.ok(productMapper.toResponse(productService.findProductsByTagIgnoreCase(tag)));
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<ProductResponseDto>> getProductsByCategory(
            @PathVariable Integer category
    ) {
        return ResponseEntity.ok(productMapper.toResponse(productService.findByCategory(category)));
    }

    @GetMapping("/isItem/{product_id}")
    public ResponseEntity<Boolean> getIsItem(
            @PathVariable Long product_id
    ) {
        return ResponseEntity.ok(productService.getIsItem(product_id));
    }

    @GetMapping("/getProductByName/{name}")
    public ResponseEntity<List<ProductResponseDto>> getProductsByName(
            @PathVariable String name
    ) {
        return ResponseEntity.ok(productMapper.toResponse(productService.findByNameContainsIgnoreCase(name)));
    }
}

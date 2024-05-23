package alexDavid.controller;

import alexDavid.dtos.WishListDTO.WishListRequestDto;
import alexDavid.mappers.WishListMapper;
import alexDavid.models.WishList;
import alexDavid.service.WishListService.WishListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/divehub/wishes")
@RequiredArgsConstructor
@CrossOrigin
@Slf4j
public class WishListController {
    private final WishListService wishListService;
    private final WishListMapper wishListMapper;

    @GetMapping("/{email}")
    public ResponseEntity<List<WishList>> getListByUser(
            @PathVariable String email
    ){
        return ResponseEntity.ok(wishListService.getListByUser(email));
    }

    @GetMapping("/{productId}/{email}")
    public ResponseEntity<Boolean> isProductOnWishList (
            @PathVariable String email,
            @PathVariable Long productId
    ){
        if (wishListService.isItem(productId)) return ResponseEntity.ok(wishListService.isProductLiked(email, productId, null));
        return ResponseEntity.ok(wishListService.isProductLiked(email, null, productId));
    }

    @GetMapping("/findType/{productId}")
    public ResponseEntity<Boolean> isItemOrActivity (
            @PathVariable Long productId
    ){
        return ResponseEntity.ok(wishListService.isItem(productId));
    }

    @PostMapping("/add")
    public ResponseEntity<?> addProduct(
            @RequestBody WishListRequestDto wishListRequestDto
    ){
        WishList wishList = wishListMapper.toModel(wishListRequestDto);
        wishListService.addProduct(wishList);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/clean/{productId}/{email}")
    public ResponseEntity<?> removeProduct (
        @PathVariable Long productId,
        @PathVariable String email
    ){
        if (wishListService.isItem(productId)) wishListService.removeItem(productId, email);
        else wishListService.removeActivity(productId, email);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/cleanAll/{email}")
    public ResponseEntity<?> removeAllProducts(
            @PathVariable String email
    ){
        wishListService.cleanWishList(email);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

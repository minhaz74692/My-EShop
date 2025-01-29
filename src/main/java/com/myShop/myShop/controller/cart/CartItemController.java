package com.myShop.myShop.controller.cart;

import com.myShop.myShop.Response.ApiResponse;
import com.myShop.myShop.dto.CartItemDto;
import com.myShop.myShop.exception.ResourseNotFoundException;
import com.myShop.myShop.request.AddCartItemRequest;
import com.myShop.myShop.service.cart.ICartItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;


@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/cart-item")
public class CartItemController {
    private final ICartItemService cartItemService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createCartItem(@Valid @RequestBody AddCartItemRequest request){
        try {
          return ResponseEntity.ok(new ApiResponse("Created!", cartItemService.createCartItem(request)))  ;
        }catch (RuntimeException e){
          return   ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getCartItem(@PathVariable Long id){
       CartItemDto item=   this.getItem(id);
       if(item ==null){
           return  ResponseEntity.status(NOT_FOUND).body(new ApiResponse("NOT Found!", null));
       }
        return  ResponseEntity.ok(new ApiResponse("Success!", item));
    }

    public CartItemDto getItem(Long id){
        try {
            return cartItemService.getCartItem(id);
        }catch (ResourseNotFoundException e){
          return null;
        }
    }

    @DeleteMapping("/delete/{id}")
    public  ResponseEntity<ApiResponse> deleteCartItemById(@PathVariable Long id){
        CartItemDto item=   this.getItem(id);
        if(item ==null){
            return  ResponseEntity.status(NOT_FOUND).body(new ApiResponse("NOT Found!", null));
        }
        try{
            cartItemService.removeCartItemById(id);
            return ResponseEntity.ok(new ApiResponse("Success", null));
        }catch (RuntimeException e){
            return  ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }
}

package com.myShop.myShop.controller.cart;

import com.myShop.myShop.Response.ApiResponse;
import com.myShop.myShop.request.AddCartItemRequest;
import com.myShop.myShop.service.cart.ICartItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<ApiResponse> createCartItem(@PathVariable Long id){
        try {
          return ResponseEntity.ok(new ApiResponse("Success!", cartItemService.getCartItem(id)))  ;
        }catch (RuntimeException e){
          return   ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }
}

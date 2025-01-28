package com.myShop.myShop.controller.cart;

import com.myShop.myShop.Response.ApiResponse;
import com.myShop.myShop.exception.ResourseNotFoundException;
import com.myShop.myShop.service.cart.ICartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/cart")
public class CartController {
    private final ICartService cartService;



    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getCart(@PathVariable Long id){
        try{
            return ResponseEntity.ok(new ApiResponse("Success", cartService.getCart(id)));
        }catch (ResourseNotFoundException e){
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteCart(@PathVariable Long id){
        try{
            cartService.clearCart(id);
            return ResponseEntity.ok(new ApiResponse("Success", null));
        }catch (Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

}

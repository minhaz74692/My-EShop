package com.myShop.myShop.controller.product;

import com.myShop.myShop.Response.ApiResponse;
import com.myShop.myShop.model.Product;
import com.myShop.myShop.request.AddProductRequest;
import com.myShop.myShop.service.product.IProductService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/product")
public class ProductController {
    private final IProductService productService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllProduct(){
        try{
            return  ResponseEntity.ok(new ApiResponse("Success!", productService.getAllProducts()));
        } catch (Exception e) {
            return  ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Error: "+ e.getMessage(), null));
        }
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> addProduct(@RequestBody AddProductRequest request){
        try {
            Product product = productService.addProduct(request);
            return  ResponseEntity.ok(new ApiResponse("Success!", product));
        } catch (Exception e) {
            return  ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Error: "+ e.getMessage(), null));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long id){
        try{
            productService.deleteProductById(id);
            return ResponseEntity.ok(new ApiResponse("Deleted!", null));
        }catch (Exception e){
            return  ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Error: "+ e.getMessage(), null));
        }
    }


}

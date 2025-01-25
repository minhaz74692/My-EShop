package com.myShop.myShop.controller.product;

import com.myShop.myShop.Response.ApiResponse;
import com.myShop.myShop.model.Category;
import com.myShop.myShop.model.Product;
import com.myShop.myShop.request.AddProductRequest;
import com.myShop.myShop.service.product.IProductService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/products")
public class ProductController {
    private final IProductService productService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> addProduct(@RequestBody AddProductRequest request){
        try {
            Product product = productService.addProduct(request);
            return  ResponseEntity.ok(new ApiResponse("Success!", product));
        } catch (Exception e) {
            return  ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Error: "+ e.getMessage(), null));
        }
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllProduct(){
        try{
            return  ResponseEntity.ok(new ApiResponse("Success!", productService.getAllProducts()));
        } catch (Exception e) {
            return  ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Error: "+ e.getMessage(), null));
        }
    }

    @GetMapping("/name/{productName}")
    public ResponseEntity<ApiResponse> getProductById(@PathVariable String productName){
        try{
            return  ResponseEntity.ok(new ApiResponse("Success!", productService.getProductsByName(productName)));
        }catch (Exception e){
            return  ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Error: ", e.getMessage()));
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ApiResponse> getProductById(@PathVariable Long id){
        try{
            Product product = productService.getProductById(id);
            if(product != null){
                return  ResponseEntity.ok(new ApiResponse("Success!", product));
            }
         return  ResponseEntity.status(NOT_FOUND).body(new ApiResponse("Product Not Found!", null));
        }catch (Exception e){
            return  ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Error: ", e.getMessage()));
        }
    }

    @GetMapping("/category-name/{categoryName}")
    public ResponseEntity<ApiResponse> getProductByCategoryName(@PathVariable String categoryName){
        try{
            return  ResponseEntity.ok(new ApiResponse("Success!", productService.getProductsByCategoryName(categoryName)));
        }catch (Exception e){
            return  ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Error: ", e.getMessage()));
        }
    }

    @GetMapping("/category-id/{categoryId}")
    public ResponseEntity<ApiResponse> getProductByCategoryId(@PathVariable Long categoryId){
        try{
            return  ResponseEntity.ok(new ApiResponse("Success!", productService.getProductsByCategory(categoryId)));
        }catch (Exception e){
            return  ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Error: ", e.getMessage()));
        }
    }


    @GetMapping("/brand/{brandName}")
    public ResponseEntity<ApiResponse> getProductByBrandName(@PathVariable String brandName){
        try{
            return  ResponseEntity.ok(new ApiResponse("Success!", productService.getProductsByBrand(brandName)));
        }catch (Exception e){
            return  ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Error: ", e.getMessage()));
        }
    }

    @GetMapping("/category-brand/{brandName}/{categoryName}")
    public ResponseEntity<ApiResponse> getProductByBrandNameAndCategory(@PathVariable String brandName, @PathVariable String categoryName){
        try{
            return  ResponseEntity.ok(new ApiResponse("Success!", productService.getProductsByCategoryAndBrand(categoryName,brandName)));
        }catch (Exception e){
            return  ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Error: ", e.getMessage()));
        }
    }

    @GetMapping("/brand-name/{brandName}/{name}")
    public ResponseEntity<ApiResponse> getProductByBrandNameAndName(@PathVariable String brandName, @PathVariable String name){
        try{
            return  ResponseEntity.ok(new ApiResponse("Success!", productService.getProductsByBrandAndName(brandName, name)));
        }catch (Exception e){
            return  ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Error: ", e.getMessage()));
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

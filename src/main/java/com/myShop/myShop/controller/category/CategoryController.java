package com.myShop.myShop.controller.category;

import com.myShop.myShop.Response.ApiResponse;
import com.myShop.myShop.model.Category;
import com.myShop.myShop.service.category.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/category")
public class CategoryController {
    private  final ICategoryService categoryService;

    @GetMapping("/all")
    private ResponseEntity<ApiResponse> getAllCategory(){
        try {
            List<Category> categoryList = categoryService.getAllCategories();
            return  ResponseEntity.ok(new ApiResponse("Found!", categoryList));
        } catch (Exception e) {
           return  ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Error:"+ e.getMessage(), INTERNAL_SERVER_ERROR));
        }
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> addCategory(@RequestBody Category category){
        try {
            Category newCategory = categoryService.addCategory(category);
            return  ResponseEntity.ok(new ApiResponse("created!", category));
        } catch (Exception e) {
            return ResponseEntity.status(CONFLICT).body(new ApiResponse("Error: "+ e.getMessage(), null));
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ApiResponse> getCategoryById(@PathVariable Long id){
        try {
            Category category = categoryService.getCategoryById(id);
            if(category != null){
                return  ResponseEntity.ok(new ApiResponse("Success!", category));
            }else{
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("Not Found!", null));
            }
        } catch (Exception e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("Error: "+ e.getMessage(), null));
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<ApiResponse> getCategoryByName(@PathVariable String name){
        try {
            Category category = categoryService.getCategoryByName(name);
            if(category != null){
                return  ResponseEntity.ok(new ApiResponse("Success!", category));
            }else{
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("Not Found!", null));
            }
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Error: "+ e.getMessage(), null));
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable Long id, @RequestBody Category category){
        try {
            Category updatedCategory = categoryService.updateCategory(category, id);
            return  ResponseEntity.ok(new ApiResponse("Update Success!", updatedCategory));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Error: "+ e.getMessage(), null));
        }
    }

    @DeleteMapping("/delete/{categoryId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Long categoryId){
        try {
            categoryService.deleteCategory(categoryId);
            return  ResponseEntity.ok(new ApiResponse("deleted!", null));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Error: "+ e.getMessage(), null));
        }
    }
}

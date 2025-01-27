package com.myShop.myShop.service.product;

import com.myShop.myShop.dto.ProductDto;
import com.myShop.myShop.model.Product;
import com.myShop.myShop.request.AddProductRequest;
import com.myShop.myShop.request.ProductUpdateRequest;

import java.util.List;

public interface IProductService {
    Product addProduct(AddProductRequest request);
    Product getProductById(Long id);
    void deleteProductById(Long id);
    Product updateProduct(ProductUpdateRequest request, Long productId);

    List<Product> getAllProducts();
    List<Product> getProductsByCategoryName(String category);
    List<Product> getProductsByCategory(Long id);
    List<Product> getProductsByBrand(String brand);
    List<Product> getProductsByCategoryAndBrand(String category,String brand);
    List<Product> getProductsByName(String name);
    List<Product> getProductsByBrandAndName(String brand,String name);
    Long countProductsByBrandAndName(String brand,String name);
    Long countProducts();
    ProductDto convertToDto(Product product);

}

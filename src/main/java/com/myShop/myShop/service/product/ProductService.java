package com.myShop.myShop.service.product;

import com.myShop.myShop.exception.ProductNotFoundException;
import com.myShop.myShop.model.Category;
import com.myShop.myShop.model.Product;
import com.myShop.myShop.repository.CategoryRepository;
import com.myShop.myShop.repository.ProductRepository;
import com.myShop.myShop.request.AddProductRequest;
import com.myShop.myShop.request.ProductUpdateRequest;
import com.myShop.myShop.service.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService implements  IProductService{
    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    @Override
    public Product addProduct(AddProductRequest addProductRequest) {
        Product product = addProductRequest.createProduct();
        Category category = categoryService.getCategoryById(addProductRequest.getCategoryId());
        if(category!=null){
        product.setCategory(category);
         return productRepository.save(product);
        }
        return null;
    }


    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(()-> new ProductNotFoundException("Product Not Found with this id"));
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.findById(id).ifPresentOrElse(productRepository:: delete, ()-> {
            throw new ProductNotFoundException("Product not found!");
        });
    }

    @Override
    public Product updateProduct(ProductUpdateRequest request, Long productId) {
        return productRepository.findById(productId)
                .map(existingProduct -> updateExistingProduct(existingProduct, request))
                .map(productRepository:: save)
                .orElseThrow(()-> new ProductNotFoundException("Product Not Found!"));
    }

    private Product updateExistingProduct(Product existingProduct, ProductUpdateRequest request){
        existingProduct.setName(request.getName());
        existingProduct.setBrand(request.getBrand());
        existingProduct.setPrice(request.getPrice());
        existingProduct.setInventory(request.getInventory());
        existingProduct.setDescription(request.getDescription());

        Category category =categoryService.getCategoryById(request.getCategoryId());
        existingProduct.setCategory(category);
        return  existingProduct;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductsByCategoryName(String category) {
        return productRepository.findByCategoryName(category);
    }

    @Override
    public List<Product> getProductsByCategory(Long id) {
        return productRepository.findByCategoryId(id);
    }

    @Override
    public List<Product> getProductsByBrand(String brand) {
        return productRepository.findByBrand(brand);
    }

    @Override
    public List<Product> getProductsByCategoryAndBrand(String category, String brand) {
        return productRepository.findByCategoryNameAndBrand(category,brand);
    }

    @Override
    public List<Product> getProductsByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public List<Product> getProductsByBrandAndName(String brand, String name) {
        return productRepository.findByBrandAndName(brand,name);
    }

    @Override
    public Long countProductsByBrandAndName(String brand, String name) {
        return productRepository.countProductsByBrandAndName(brand,name);
    }

    @Override
    public Long countProducts() {
        return productRepository.count();
    }
}

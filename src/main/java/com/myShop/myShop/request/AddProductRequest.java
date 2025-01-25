package com.myShop.myShop.request;

import com.myShop.myShop.model.Category;
import com.myShop.myShop.model.Image;
import com.myShop.myShop.model.Product;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Data
public class AddProductRequest {
    private Long id;
    private  String name;
    private String brand;
    private BigDecimal price;
    private int inventory;
    private  String description;
    private Long categoryId;

    public Product createProduct(){
        return Product.builder()
                .name(this.name)
                .description(this.description)
                .price(this.price)
                .brand(this.brand)
                .inventory(this.inventory)
                .build();

    }
}

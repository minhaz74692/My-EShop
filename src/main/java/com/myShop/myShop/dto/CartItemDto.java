package com.myShop.myShop.dto;

import com.myShop.myShop.model.Cart;
import com.myShop.myShop.model.CartItem;
import com.myShop.myShop.model.Product;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Data
public class CartItemDto {
    private Long id;
    private int quantity;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;
    private ProductDto product;
    private Long cartId;
}

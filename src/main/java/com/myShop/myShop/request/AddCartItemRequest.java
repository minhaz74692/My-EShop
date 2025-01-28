package com.myShop.myShop.request;

import com.myShop.myShop.model.Cart;
import com.myShop.myShop.model.CartItem;
import com.myShop.myShop.model.Product;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AddCartItemRequest {
    private Long id;
    private int quantity;
    private Long productId;
    private Long cartId;
}

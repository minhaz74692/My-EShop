package com.myShop.myShop.service.cart;

import com.myShop.myShop.dto.CartDto;
import com.myShop.myShop.model.Cart;

import java.math.BigDecimal;

public interface ICartService {
    CartDto getCart(Long id);
    void clearCart(Long id);
    BigDecimal getTotalAmount(Long id);
    CartDto convertToDto(Cart cart);
}

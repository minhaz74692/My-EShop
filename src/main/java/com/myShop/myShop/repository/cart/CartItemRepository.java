package com.myShop.myShop.repository.cart;

import com.myShop.myShop.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    CartItem getCartItemByCartIdAndProductId(Long cartId, Long productId);
}

package com.myShop.myShop.service.cart;

import com.myShop.myShop.dto.CartItemDto;
import com.myShop.myShop.model.CartItem;
import com.myShop.myShop.request.AddCartItemRequest;

public interface ICartItemService {
    CartItemDto getCartItem(Long id);
    CartItemDto createCartItem(AddCartItemRequest cartItem);
    CartItemDto convertToDto(CartItem item);
    void removeCartItemById(Long id);
    CartItem getCartItemByCartIdAndProductId(Long cartId, Long ProductId);
}

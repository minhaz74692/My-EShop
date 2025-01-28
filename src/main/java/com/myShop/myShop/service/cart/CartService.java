package com.myShop.myShop.service.cart;

import com.myShop.myShop.dto.CartDto;
import com.myShop.myShop.dto.CartItemDto;
import com.myShop.myShop.exception.ResourseNotFoundException;
import com.myShop.myShop.model.Cart;
import com.myShop.myShop.model.CartItem;
import com.myShop.myShop.repository.cart.CartRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CartService implements ICartService{
    private final CartRepository cartRepository;
    private final ModelMapper modelMapper;
    private final CartItemService cartItemService;

    @Override
    public CartDto getCart(Long id) {
        return this.convertToDto(cartRepository.findById(id).orElseThrow(()-> new ResourseNotFoundException("Cart is empty")));
    }

    @Override
    public void clearCart(Long id) {
        cartRepository.deleteById(id);
    }

    @Override
    public BigDecimal getTotalAmount(Long id) {
        return null;
    }

    @Override
    public CartDto convertToDto(Cart cart){
        CartDto cartDto = modelMapper.map(cart, CartDto.class);
        Set<CartItemDto> itemDtos =new HashSet<>();
        for(CartItem cartItem:cart.getCartItems()){
            itemDtos.add(cartItemService.convertToDto(cartItem));
        }
        cartDto.setCartItems(itemDtos);
        return cartDto;
    }
}

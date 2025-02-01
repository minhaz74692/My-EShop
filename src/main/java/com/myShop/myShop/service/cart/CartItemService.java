package com.myShop.myShop.service.cart;

import com.myShop.myShop.dto.CartItemDto;
import com.myShop.myShop.dto.ImageDto;
import com.myShop.myShop.dto.ProductDto;
import com.myShop.myShop.exception.ResourseNotFoundException;
import com.myShop.myShop.model.Cart;
import com.myShop.myShop.model.CartItem;
import com.myShop.myShop.model.Image;
import com.myShop.myShop.model.Product;
import com.myShop.myShop.repository.cart.CartItemRepository;
import com.myShop.myShop.repository.cart.CartRepository;
import com.myShop.myShop.request.AddCartItemRequest;
import com.myShop.myShop.service.product.IProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import static org.springframework.data.util.Optionals.ifPresentOrElse;

@Service
@RequiredArgsConstructor
public class CartItemService implements ICartItemService{
    private  final CartItemRepository cartItemRepository;
    private  final CartRepository cartRepository;
    private  final IProductService productService;
    private final ModelMapper modelMapper;

    @Override
    public CartItemDto getCartItem(Long id) {
        return this.convertToDto(cartItemRepository.findById(id).orElseThrow(() -> new ResourseNotFoundException("Cart item not found!"))) ;
    }

    @Override
    public CartItemDto createCartItem(AddCartItemRequest addCartItemRequest) {
        Product product = productService.getProductById(addCartItemRequest.getProductId());
        CartItem existingItem = getCartItemByCartIdAndProductId(addCartItemRequest.getCartId(), addCartItemRequest.getProductId());
        if(existingItem != null){
            existingItem.setQuantity(existingItem.getQuantity() + addCartItemRequest.getQuantity());
            existingItem.setCustomTotalPrice();
            Cart existinggCart = existingItem.getCart();
            existinggCart.addItem(existingItem);
            return  this.convertToDto(cartItemRepository.save(existingItem));
        }
        CartItem item = new CartItem();
        item.setQuantity(addCartItemRequest.getQuantity());
        item.setProduct(product);
        item.setUnitPrice(product.getPrice());
        item.setCustomTotalPrice();

        Cart cart =  cartRepository.findById(addCartItemRequest.getCartId() == null ?-1:addCartItemRequest.getCartId())
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                  return cartRepository.save(newCart);
                });

        cart.addItem(item);
        return this.convertToDto(cartItemRepository.save(item));
    }

    @Override
    public void removeCartItemById(Long id){
        cartItemRepository.deleteById(id);
    }

    @Override
    public CartItem getCartItemByCartIdAndProductId(Long cartId, Long ProductId) {
       return cartItemRepository.getCartItemByCartIdAndProductId(cartId, ProductId);
    }

    @Override
    public CartItemDto convertToDto(CartItem item){
        CartItemDto cartItemDto = modelMapper.map(item, CartItemDto.class);
        cartItemDto.setProduct(productService.convertToDto(item.getProduct()));
        return  cartItemDto;
    }
}

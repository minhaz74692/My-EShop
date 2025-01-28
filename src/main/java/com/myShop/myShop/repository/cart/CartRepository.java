package com.myShop.myShop.repository.cart;

import com.myShop.myShop.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {

}

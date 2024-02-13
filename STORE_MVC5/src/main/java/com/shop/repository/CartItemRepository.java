package com.shop.repository;

import com.shop.entity.Cart;
import com.shop.entity.CartItem;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
	List<CartItem> findByCartAndItemId(Long userId, Long itemId);
    List<CartItem> findByCart(Cart cart);
}
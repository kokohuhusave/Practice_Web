package com.shop.shopservice.implement;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.shop.entity.Cart;
import com.shop.entity.CartItem;
import com.shop.entity.Item;
import com.shop.entity.User;
import com.shop.repository.*;

import com.shop.shopservice.CartItemService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor 
public class CartItemServiceImplement implements CartItemService<CartItem, Long> {

	private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;
    private final ItemRepository itemRepository;
    
    @Override
    public CartItem add(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }

    @Override
    public CartItem update(CartItem cartItem) {
        CartItem existingCartItem = cartItemRepository.findById(cartItem.getId())
            .orElseThrow(() -> new RuntimeException("CartItem이 없습니다."));
        existingCartItem.setCount(cartItem.getCount());
        existingCartItem.setItem(cartItem.getItem());
        return cartItemRepository.save(existingCartItem);
    }

    @Override
    public List<CartItem> getAll() {
        return cartItemRepository.findAll();
    }

    @Override
    public CartItem getById(Long id) {
        return cartItemRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("CartItem이 없습니다."));
    }

    @Override
    public void deleteById(Long id) {
        cartItemRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("CartItem이 없습니다."));
        cartItemRepository.deleteById(id);
    }
    
    @Override
    public void addItemToCart(Long userId, Long itemId) {
    	Cart cart = cartRepository.findById(userId).orElseThrow(() -> new RuntimeException("Cart not found"));
        Item item = itemRepository.findById(itemId).orElseThrow(() -> new RuntimeException("Item not found"));
    	CartItem cartItem = new CartItem();
    	cartItem.setCart(cart); 
    	cartItem.setItem(item); 
        
        cartItemRepository.save(cartItem);
    }

    @Override
    public void removeItemFromCart(Long userId, Long itemId) {
        List<CartItem> cartItems = cartItemRepository.findByCartAndItemId(userId, itemId);
        cartItemRepository.deleteAll(cartItems);
    }

    @Override
    public List<Item> getItemsInCart(Cart cart) {
        List<CartItem> cartItems = cartItemRepository.findByCart(cart);
        return cartItems.stream().map(CartItem::getItem).collect(Collectors.toList());
    }
}

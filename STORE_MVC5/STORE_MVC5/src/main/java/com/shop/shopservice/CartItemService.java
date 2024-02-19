package com.shop.shopservice;

import java.util.List;


import com.shop.entity.Cart;
import com.shop.entity.CartItem;
import com.shop.entity.Item;

public interface CartItemService<T, U> extends CommonService<T, U> {
	 void addItemToCart(Long userId, U itemId);
	 void removeItemFromCart(Long userId, U itemId);
	 List<Item> getItemsInCart(Cart cart);
	 
}
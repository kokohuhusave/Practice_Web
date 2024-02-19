package com.shop.biz.implement;

import java.util.List;

import com.shop.biz.ShopBizService;
import com.shop.config.BizService;
import com.shop.entity.Cart;
import com.shop.entity.CartItem;
import com.shop.entity.Item;
import com.shop.entity.User;
import com.shop.shopservice.CartItemService;
import com.shop.shopservice.CartService;
import com.shop.shopservice.ItemService;
import com.shop.shopservice.UserService;

import lombok.RequiredArgsConstructor;

@BizService
@RequiredArgsConstructor
public class ShopBizServiceImplement implements ShopBizService {

    private final UserService<User, Long> userService;
    private final CartService<Cart, Long> cartService;
    private final CartItemService<CartItem, Long> cartItemService;
    private final ItemService<Item, Long> itemService;

    @Override
    public void addItemToUserCart(Long userId, Long itemId) {
        User user = userService.getById(userId);
        Item item = itemService.getById(itemId);
        Cart cart = cartService.getCartByUserId(userId);

        cartItemService.addItemToCart(userId, itemId); 
    }

    @Override
    public void removeItemFromUserCart(Long userId, Long itemId) {
        Cart cart = cartService.getCartByUserId(userId);
        cartItemService.removeItemFromCart(userId, itemId); 
    }

    @Override
    public List<Item> getUserCartItems(Long userId) {
        Cart cart = cartService.getCartByUserId(userId);
        return cartItemService.getItemsInCart(cart);
    }
}



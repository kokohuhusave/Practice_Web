package com.shop.shopservice;

import com.shop.entity.Cart;

public interface CartService<T, U> extends CommonService<T, U> {
    Cart getCartByUserId(Long userId);
}

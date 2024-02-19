package com.shop.biz;

import java.util.List;
import com.shop.entity.Item;

public interface ShopBizService {
	void addItemToUserCart(Long userId, Long itemId);
    void removeItemFromUserCart(Long userId, Long itemId);
    List<Item> getUserCartItems(Long userId);
}
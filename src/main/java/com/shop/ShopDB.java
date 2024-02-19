package com.shop;

import com.shop.constant.ItemSellStatus;
import com.shop.entity.Item;
import com.shop.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ShopDB implements CommandLineRunner {

    private final ItemRepository itemRepository;

    @Autowired
    public ShopDB(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Item item = new Item();
        item.setItemname("토비의 스프링 3.1");
        item.setPrice(40000);
        item.setStockNumber(1);
        item.setStatus(ItemSellStatus.SOLD_OUT);
        itemRepository.save(item);
    }
}

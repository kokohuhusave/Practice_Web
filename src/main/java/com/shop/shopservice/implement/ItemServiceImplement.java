package com.shop.shopservice.implement;

import java.util.List;
import org.springframework.stereotype.Service;

import com.shop.constant.ItemSellStatus;
import com.shop.entity.Item;
import com.shop.repository.ItemRepository;
import com.shop.shopservice.ItemService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor 
public class ItemServiceImplement implements ItemService<Item, Long> {
    
    private final ItemRepository itemRepository;

    @Override
    public Item add(Item singleEntity) {
        singleEntity.setStatus(ItemSellStatus.SELL);
        return itemRepository.save(singleEntity);
    }

    @Override
    public Item update(Item singleEntity) {
        Item existingItem = itemRepository.findById(singleEntity.getId())
            .orElseThrow(() -> new RuntimeException("Item이 없습니다"));
        existingItem.setItemname(singleEntity.getItemname());
        existingItem.setPrice(singleEntity.getPrice());
        existingItem.setStockNumber(singleEntity.getStockNumber());
        existingItem.setStatus(singleEntity.getStatus());
        return itemRepository.save(existingItem);
    }

    @Override
    public List<Item> getAll() {
        return itemRepository.findAll();
    }

    @Override
    public Item getById(Long id) {
        return itemRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Item이 없습니다"));
    }

    @Override
    public void deleteById(Long id) {
        itemRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Item이 없습니다"));
        itemRepository.deleteById(id);
    }
}

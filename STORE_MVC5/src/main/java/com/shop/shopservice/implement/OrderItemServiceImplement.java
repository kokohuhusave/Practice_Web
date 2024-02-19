package com.shop.shopservice.implement;

import java.util.List;
import org.springframework.stereotype.Service;
import com.shop.entity.OrderItem;
import com.shop.repository.OrderItemRepository;
import com.shop.shopservice.OrderItemService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor 
public class OrderItemServiceImplement implements OrderItemService<OrderItem, Long>{

    private final OrderItemRepository orderItemRepository;

    @Override
    public OrderItem add(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    @Override
    public OrderItem update(OrderItem orderItem) {
        OrderItem existingOrderItem = orderItemRepository.findById(orderItem.getId())
                .orElseThrow(() -> new RuntimeException("OrderItem이 없습니다"));
        existingOrderItem.setOrder(orderItem.getOrder());
        existingOrderItem.setItem(orderItem.getItem());
        existingOrderItem.setPrice(orderItem.getPrice());
        existingOrderItem.setCount(orderItem.getCount());
        return orderItemRepository.save(existingOrderItem);
    }

    @Override
    public List<OrderItem> getAll() {
        return orderItemRepository.findAll();
    }

    @Override
    public OrderItem getById(Long id) {
        return orderItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order Item이 없습니다"));
    }

    @Override
    public void deleteById(Long id) {
        orderItemRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("OrderItem not found with id: " + id));
        orderItemRepository.deleteById(id);
    }
}

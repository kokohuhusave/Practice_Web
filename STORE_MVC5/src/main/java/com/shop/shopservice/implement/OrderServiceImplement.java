package com.shop.shopservice.implement;

import java.util.List;


import org.springframework.stereotype.Service;

import com.shop.entity.Order;
import com.shop.repository.OrderRepository;
import com.shop.shopservice.OrderService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImplement implements OrderService<Order, Long>{
	
	private final OrderRepository orderRepository;

    @Override
    public Order add(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order update(Order order) {
        Order existingOrder = orderRepository.findById(order.getId())
                .orElseThrow(() -> new RuntimeException("Order이 없습니다."));
        existingOrder.setOrderDate(order.getOrderDate());
        existingOrder.setUser(order.getUser());
        existingOrder.setStatus(order.getStatus());
        existingOrder.setOrderItems(order.getOrderItems());
        return orderRepository.save(existingOrder);
    }

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order getById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order이 없습니다."));
    }

    @Override
    public void deleteById(Long id) {
        orderRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Order이 없습니다."));
        orderRepository.deleteById(id);
    }
}

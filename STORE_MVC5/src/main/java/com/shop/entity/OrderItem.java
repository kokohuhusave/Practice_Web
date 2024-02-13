package com.shop.entity;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class OrderItem {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToOne
    private Order order;

    @ManyToOne
    private Item item;
    
    private int price;
    private int count;

    public Order getOrder() {
        return this.order;
    }

    public Item getItem() {
        return this.item;
    }
}
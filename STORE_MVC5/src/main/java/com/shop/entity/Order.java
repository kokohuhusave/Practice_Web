package com.shop.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "`order`")
@Getter
@Setter
public class Order {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private LocalDateTime orderDate;

    @ManyToOne
    private User user;
    
    private String status;

    @OneToMany(mappedBy = "order")
    private Set<OrderItem> orderItems;

    public Set<OrderItem> getOrderItems() {
        return this.orderItems;
    }

    public User getUser() {
        return this.user;
    }
}
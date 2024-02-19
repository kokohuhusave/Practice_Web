package com.shop.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.shop.constant.ItemSellStatus;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; //상품 ID값
    
    private String itemname; //상품명
    private int price;	//상품 가격
    private int stockNumber;	//재고
    @Enumerated(EnumType.STRING)
    private ItemSellStatus status;	//상품 판매 현황
    private String description;
//    private Cart cart;

}
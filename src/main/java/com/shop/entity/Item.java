package com.shop.entity;

import javax.persistence.*;
import org.apache.commons.text.StringEscapeUtils;
import com.shop.constant.ItemSellStatus;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "items") // 예약어 회피
@Getter
@Setter
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemname;
    private int price;
    private int stockNumber;
    
    @Enumerated(EnumType.STRING)
    private ItemSellStatus status;
    
    private String description;
    
//    //HTML escape 처리
//    public void setItemname(String itemname) {
//        this.itemname = StringEscapeUtils.escapeHtml4(itemname);
//    }
//    
//    public void setDescription(String description) {
//        this.description = StringEscapeUtils.escapeHtml4(description);
//    }
    
    
    
}

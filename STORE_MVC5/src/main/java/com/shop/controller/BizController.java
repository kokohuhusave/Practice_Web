package com.shop.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.biz.ShopBizService;
import com.shop.entity.Item;
import com.shop.enums.BizStatusCode;
import com.shop.exception.CustomException;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/shop")
@RequiredArgsConstructor
public class BizController {

    private final ShopBizService shopBizService;

    @PostMapping("/cart-items/{userId}/{itemId}")
    public ResponseEntity<String> addItemToUserCart(@PathVariable Long userId, @PathVariable Long itemId) {
        shopBizService.addItemToUserCart(userId, itemId);
        return ResponseEntity.status(HttpStatus.OK).body("아이템을 카드에 담았습니다");
    }

    @DeleteMapping("/cart-items/{userId}/{itemId}")
    public ResponseEntity<String> removeItemFromUserCart(@PathVariable Long userId, @PathVariable Long itemId) {
        shopBizService.removeItemFromUserCart(userId, itemId);
        return ResponseEntity.status(HttpStatus.OK).body("아이템을 카드에서 제거했습니다");
    }

    @GetMapping("/cart-items/{userId}")
    public ResponseEntity<List<Item>> getUserCartItems(@PathVariable Long userId) {
        List<Item> items = shopBizService.getUserCartItems(userId);
        return ResponseEntity.ok(items);
    }
    
//    @GetMapping("/custom-exception-test")
//    public ResponseEntity<?> CustomException(){
//    	throw new CustomException("Test", HttpStatus.BAD_GATEWAY);
//    }
    @GetMapping("/custom-exception-test")
    public ResponseEntity<?> customExceptionTest() {
        throw new CustomException("Test", BizStatusCode.SUCCESS); // BizStatusCode 사용
    }
}

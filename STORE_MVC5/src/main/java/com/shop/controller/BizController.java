package com.shop.controller;

import org.springframework.http.MediaType;

import java.util.List;
import java.util.Locale;

import org.apache.commons.text.StringEscapeUtils;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shop.biz.ShopBizService;
import com.shop.entity.Cart;
import com.shop.entity.Item;
import com.shop.enums.BizStatusCode;
import com.shop.exception.CustomException;
import com.shop.shopservice.CartService;
import com.shop.shopservice.ItemService;
import com.shop.config.*;
import lombok.RequiredArgsConstructor;
import com.shop.shopservice.ItemService;

@RestController
@RequestMapping("/api/shop")
@RequiredArgsConstructor
public class BizController {

    private final ShopBizService shopBizService;
    private final CartService cartService;
    private final MessageSource messageSource;
    private final ItemService itemService;
    
   
    
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
    

    @GetMapping("/custom-exception-test")
    public ResponseEntity<?> customExceptionTest() {
        throw new CustomException("Test", BizStatusCode.BAD_REQUEST);
    }
    
    // test-media-type 경로로 들어오는 post요청을 처리하고 consumes 속성을 통해 JSON 형식의 데이터를
    // 요청 본문으로만 받는다
    @PostMapping(value = "/test-media-type", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> testMediaType(@RequestBody String body) {
    	// JSON 데이터를 String으로 받고 @RequestBody 통해 HTTP 요청 본문을 자바 객체로 변환
        return ResponseEntity.ok("JSON body 전달 받음");
    }
    
//    @GetMapping("/greeting")
//    public ResponseEntity<String> greeting(
//    		
//            @RequestParam(name = "name", defaultValue = "World") String name,
//            Locale locale) {
//        String welcomeMessage = messageSource.getMessage("welcome", new Object[]{name}, locale);
//        return ResponseEntity.ok(welcomeMessage);
//    }
    @GetMapping("/greeting") 
    public ResponseEntity<String> greeting(@RequestParam(name = "name", defaultValue = "World") String name, Locale locale) {
    	// RequestParam에서 name값 추출 없다면 World 사용
        if("error".equals(name)) { // 
            throw new CustomException(BizStatusCode.BAD_REQUEST);
        }
        String welcomeMessage = messageSource.getMessage("welcome", new Object[]{name}, locale);
        //locale에 맞게 welcome메세지 조회
        return ResponseEntity.ok(welcomeMessage);
    }
    
//    
//    @GetMapping("/apis/test")
//    public ResponseEntity<String> testApi(Locale locale) {
//        return ResponseEntity.ok(testMessage);
//    } 

    @GetMapping("/apis/items")
    public ResponseEntity<List<Item>> getItems() {
        List<Item> items = itemService.getAll(); 
        return ResponseEntity.ok(items);
    }
    
//    @GetMapping("/apis/tests")
//    public ResponseEntity<String> testApi(Locale locale) {
//        String testMessage = "This is a response from /api/shop/apis/test endpoint.<>)";
//        return ResponseEntity.ok(testMessage);
//    } 
    
    
//    @GetMapping("/user/{userId}")
//    public ResponseEntity<Cart> getCartByUserId(@PathVariable Long userId) {
//        Cart cart = cartService.getCartByUserId(userId);
//        if(cart != null) {
//            return ResponseEntity.ok(cart);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
   
}

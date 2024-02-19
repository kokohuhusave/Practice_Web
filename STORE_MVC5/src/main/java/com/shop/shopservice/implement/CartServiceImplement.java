package com.shop.shopservice.implement;



import java.util.List;

import org.springframework.stereotype.Service;
import com.shop.entity.Cart;
import com.shop.repository.CartRepository;
import com.shop.shopservice.CartService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor 
public class CartServiceImplement implements CartService<Cart, Long> {

    private final CartRepository cartRepository;

    @Override
    public Cart add(Cart singleEntity) {
        return cartRepository.save(singleEntity);
    }

    @Override
    public Cart update(Cart cart) {
        Cart existingCart = cartRepository.findById(cart.getId())
            .orElseThrow(() -> new RuntimeException("Cart가 없습니다."));
        existingCart.setEmail(cart.getEmail());
        existingCart.setAddress(cart.getAddress());
        return cartRepository.save(existingCart);
    }

    @Override
    public List<Cart> getAll() {
        return cartRepository.findAll();
    }

    @Override
    public Cart getById(Long id) {
        return cartRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Cart가 없습니다."));
    }

    @Override
    public void deleteById(Long id) {
        cartRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Cart가 없습니다."));
        cartRepository.deleteById(id);
    }

	@Override
	public Cart getCartByUserId(Long userId) {
		return cartRepository.findByUser_Id(userId);
	}
    
}

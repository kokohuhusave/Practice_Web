package com.shop.shopservice.implement;

import com.shop.entity.User;
import com.shop.repository.UserRepository;
import com.shop.shopservice.UserService;

import java.util.List;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor 
public class UserServiceImplement implements UserService<User, Long> {
    
    private final UserRepository userRepository; 

    @Override
    public User add(User singleEntity) {
        return userRepository.save(singleEntity);
    }

    @Override
    public User update(User singleEntity) {
        User existingUser = userRepository.findById(singleEntity.getId())
            .orElseThrow(() -> new RuntimeException("User 정보가 없습니다"));
        existingUser.setUsername(singleEntity.getUsername());
        return userRepository.save(existingUser);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User 정보가 없습니다")); // orElse(null) 대신 예외 처리
    }

    @Override
    public void deleteById(Long id) {
    	userRepository.findById(id)
    		.orElseThrow(() -> new RuntimeException("User 정보가 없습니다"));
    	userRepository.deleteById(id);
    }

}

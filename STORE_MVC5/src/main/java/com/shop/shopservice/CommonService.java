package com.shop.shopservice;

import java.util.List;

public interface CommonService<T,U> {
	
    T add(T singleEntity);
    T update(T singleEntity);
    List<T> getAll();
    T getById(U id);
    void deleteById(U id);
}

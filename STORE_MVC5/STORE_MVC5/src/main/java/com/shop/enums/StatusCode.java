package com.shop.enums;

import java.util.Date;

import lombok.Getter;

public interface StatusCode {
	
    Date getTimestamp(); // 반환 함수
    String getMessage(); 
    String getDetails();

}

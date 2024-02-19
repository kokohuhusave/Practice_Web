package com.shop.config;

import java.util.Locale;

public interface MessageService { // 메세지 서비스 정의

	String getMessage(String code);
	
	String getMessage(String code, Object[] args);
}

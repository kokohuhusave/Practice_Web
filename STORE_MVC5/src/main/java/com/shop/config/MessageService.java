package com.shop.config;

import java.util.Locale;

public interface MessageService {

	String getMessage(String code);
	
	String getMessage(String code, Object[] args);
}

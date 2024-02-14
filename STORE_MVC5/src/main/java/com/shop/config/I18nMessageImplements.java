package com.shop.config;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class I18nMessageImplements implements MessageService{
	
	private final MessageSource messageSource;
	

	@Override
	public String getMessage(String code) {
		return getMessage(code, null);
	}

	@Override
	public String getMessage(String code, Object[] args) {
		return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
	}

}

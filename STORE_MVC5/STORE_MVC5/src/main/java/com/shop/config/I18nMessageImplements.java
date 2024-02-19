package com.shop.config;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service //빈에 등록
@RequiredArgsConstructor
public class I18nMessageImplements implements MessageService{
	//MessageService 인터페이스 구현체
	private final MessageSource messageSource;
	

	@Override
	public String getMessage(String code) { // 메세지 코드를 받아 해당 로케일의 메세지 조회
		return getMessage(code, null);		
	}

	@Override
	public String getMessage(String code, Object[] args) {//메세지와 배열을 받아 포멧팅된 메세지를 조회
		return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
	}

}

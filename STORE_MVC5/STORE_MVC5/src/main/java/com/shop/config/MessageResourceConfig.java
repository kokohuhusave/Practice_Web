package com.shop.config;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Configuration
public class MessageResourceConfig implements WebMvcConfigurer{
	// 다국어를 위한 메세지소스, resolver설정 , locale 변경을 위한 이터셉트 구현
	@Bean
	public MessageSource messageSource() { // 다국어 메세지를 담고 있는 파일 위치로 이동
	    ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
	    messageSource.setBasename("classpath:message/messages"); // 경로
	    messageSource.setDefaultEncoding("UTF-8"); // 인코딩
	    return messageSource;
	}


    @Bean
    public LocaleResolver localeResolver() {
        CookieLocaleResolver resolver = new CookieLocaleResolver();
        resolver.setDefaultLocale(new Locale("en")); // 기본 locale을 en으로 설정
        resolver.setCookieName("localeCookie");	// locale 정보 저장하는 cookie 저장
        resolver.setCookieMaxAge(4800);	// 지속시간 설정
        return resolver;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        // locale 변동 감지하도록 localeChangeInterceptor 사용
        localeChangeInterceptor.setParamName("lang"); // 매개변수를 통해 로케일 변경이 가능하게 만든다
        registry.addInterceptor(localeChangeInterceptor);
    }
}

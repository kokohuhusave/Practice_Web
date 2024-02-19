package com.shop.config;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

@Target(ElementType.TYPE) //어노테이션이 타입(클래스, 인터페이스 등)에 적용
@Retention(RetentionPolicy.RUNTIME) //어노테이션이 런타임에도 유지  리플렉션을 통해 해당 어노테이션 정보
@Documented
@Component
public @interface BizService {

	@AliasFor(annotation = Component.class)
	String value() default "";
}

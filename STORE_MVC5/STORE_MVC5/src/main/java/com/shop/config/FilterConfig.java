package com.shop.config;

import com.shop.filter.CustomResponseFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<CustomResponseFilter> customResponseFilter() {
        FilterRegistrationBean<CustomResponseFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new CustomResponseFilter());
        registrationBean.addUrlPatterns("/apis/*");
        return registrationBean;
    }
}


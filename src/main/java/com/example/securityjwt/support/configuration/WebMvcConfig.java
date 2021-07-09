package com.example.securityjwt.support.configuration;

import com.example.securityjwt.support.filter.CustomHeaderFilter;
import com.example.securityjwt.support.interceptor.JwtTokenInterceptor;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtTokenInterceptor()).addPathPatterns("/user/list");
    }

    @Bean
    public FilterRegistrationBean<CustomHeaderFilter> getFilterRegistrationBean() {
        FilterRegistrationBean<CustomHeaderFilter> registrationBean = new FilterRegistrationBean<>(createHeaderFilter());
        registrationBean.setOrder(Integer.MIN_VALUE);
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }

    @Bean
    public CustomHeaderFilter createHeaderFilter() {
        return new CustomHeaderFilter();
    }

    @Bean
    public JwtTokenInterceptor jwtTokenInterceptor() {
        return new JwtTokenInterceptor();
    }

}
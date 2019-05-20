package com.reed.info_manager.config;

import com.reed.info_manager.interceptors.LandingStatusInterceptor;
import com.reed.info_manager.interceptors.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Autowired
    LoginInterceptor loginInterceptor;
    @Autowired
    LandingStatusInterceptor landingStatusInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns("/login/**").excludePathPatterns("/static/**");
        registry.addInterceptor(landingStatusInterceptor).addPathPatterns("/login");
    }
}

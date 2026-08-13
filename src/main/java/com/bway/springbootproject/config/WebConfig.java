package com.bway.springbootproject.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(authInterceptor)
                .addPathPatterns(
                        "/home",
                        "/department/**",
                        "/department-list",
                        "/dept/**",
                        "/employee/**",
                        "/employee-list",
                        "/gallery",
                        "/upload",
                        "/contact",
                        "/profile"
                )
                .excludePathPatterns(
                        "/login",
                        "/signup",
                        "/register",
                        "/css/**",
                        "/js/**",
                        "/images/**",
                        "/swagger-ui/**",
                        "/v3/api-docs/**"
                );
    }
}
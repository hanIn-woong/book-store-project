package com.bookstore.domain.admin.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class AdminWebConfig implements WebMvcConfigurer {

    private final AdminInterceptor adminInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // /admin으로 시작하는 모든 경로에 대해 AdminInterceptor 적용
        registry.addInterceptor(adminInterceptor)
                .addPathPatterns("/admin/**");
    }
}

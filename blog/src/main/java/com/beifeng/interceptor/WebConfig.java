package com.beifeng.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author BeiFeng
 * @version 1.0
 * @date 2020/11/1 23:41
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // 要拦截admin下的所有请求，必须用户登录后才可访问，但是这样拦截的路径有一些是不需要用户登录也可以访问的

        String[] addPathPatterns = {
                "/admin/**",
        };

        String[] excludePathPatterns = {
                "/admin",
                "/admin/login"
        };

        // 要排除的路径，排除的路径不需要用户登录也可以访问
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns(addPathPatterns).excludePathPatterns(excludePathPatterns);

    }
}

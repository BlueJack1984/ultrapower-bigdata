package com.example.admin.config.login.token;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 配置拦截器 对登录和注册界面的请求不拦截
 * @author daniel
 * @date 2019-12-23
 */
@Configuration
public class TokenInterceptorConfig implements WebMvcConfigurer {

    /**
     * 添加拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TokenInterceptor()).excludePathPatterns("/**");
    }

    /**
     * 自定义拦截器注入Bean, 其实没卵用!
     * @author daniel
     * @return
     */
    @Bean
    public TokenInterceptor TokenInterceptor() {
        return new TokenInterceptor();
    }
}



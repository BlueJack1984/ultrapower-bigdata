package com.example.admin.config.login.redis;

import com.example.core.constants.AnonymousAccessUrl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * 配置拦截器 对登录和注册界面的请求不拦截
 * @author daniel
 * @date 2019-12-23
 */
@Configuration
public class RedisInterceptorConfig implements WebMvcConfigurer {
    /**
     * 添加拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registration = registry.addInterceptor(new RedisInterceptor());
        registration.addPathPatterns("/**");
//        registration.excludePathPatterns(
//                AnonymousAccessUrl.LOGIN,
//                AnonymousAccessUrl.REGISTRATION,
//                AnonymousAccessUrl.CAPTCHA);
        List<String> excludePathList = new ArrayList<>();
        excludePathList.add("/captcha/**");
        registration.excludePathPatterns(excludePathList);
        //registration.excludePathPatterns("/**");
    }

    /**
     * 自定义拦截器注入Bean, 其实没卵用!
     * @author daniel
     * @return
     */
    @Bean
    public RedisInterceptor RedisInterceptor() {
        return new RedisInterceptor();
    }
}

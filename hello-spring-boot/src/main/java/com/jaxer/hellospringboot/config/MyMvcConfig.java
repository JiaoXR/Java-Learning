package com.jaxer.hellospringboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * MVC 配置类
 * <p>
 * Created by jaxer on 2018/10/5
 */
@Configuration
//@EnableWebMvc //全面接管SpringMVC配置
public class MyMvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/hello/boot").setViewName("success");
    }
}

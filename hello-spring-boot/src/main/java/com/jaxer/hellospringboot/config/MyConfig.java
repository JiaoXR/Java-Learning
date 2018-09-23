package com.jaxer.hellospringboot.config;

import com.jaxer.hellospringboot.service.HelloService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置类，用于替代 XML 配置文件【推荐使用】
 * <p>
 * Created by jaxer on 23/09/2018
 */
@Configuration
public class MyConfig {
    /**
     * 将方法的返回值添加到容器，方法名即为组件id
     */
    @Bean
    public HelloService helloService() {
        return new HelloService();
    }
}

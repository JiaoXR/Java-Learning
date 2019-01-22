package com.jaxer.example.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * 配置类
 * <p>
 * Created by jaxer on 2018/11/2
 */
@Configuration
public class Config {

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

//    @Bean
//    public IRule myRule() {
//        //负载均衡算法修改：使用随机算法替代默认的轮询算法
////        return new RandomRule();
//        return new RetryRule();
//    }
}

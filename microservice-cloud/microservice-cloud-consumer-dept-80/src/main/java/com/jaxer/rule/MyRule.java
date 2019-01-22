package com.jaxer.rule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义负载均衡算法
 * <p>
 * Created by jaxer on 2018/11/8
 */
@Configuration
public class MyRule {
    @Bean
    public IRule mySelfRule() {
        return new RandomRule();
    }
}

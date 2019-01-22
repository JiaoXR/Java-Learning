package com.jaxer.example.service;

import com.jaxer.example.domain.Dept;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by jaxer on 2018/11/11
 * 服务降级处理
 */
@Component
public class DeptClientServiceFallbackFactory implements FallbackFactory<DeptClientService> {
    @Override
    public DeptClientService create(Throwable throwable) {
        return new DeptClientService() {
            @Override
            public boolean insert(Dept dept) {
                return false;
            }

            @Override
            public Dept findById(Integer id) {
                return new Dept().setId(id)
                        .setName("该ID：" + id + "Consumer客户端提供的降级信息,服务暂时关闭")
                        .setDb(0);
            }

            @Override
            public List<Dept> findAll() {
                return null;
            }
        };
    }
}

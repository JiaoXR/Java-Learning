package com.jaxer.example.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jaxer on 2018/11/14
 * 读取远程配置文件内容
 */
@RestController
public class ConfigClientController {
    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${eureka.client.service-url.defaultZone}")
    private String eurekaServers;

    @Value("${server.port}")
    private String port;

    @RequestMapping("/config")
    public Map<String, Object> getConfig() {
        Map<String, Object> map = new HashMap<>();
        map.put("applicationName", applicationName);
        map.put("eurekaServers", eurekaServers);
        map.put("port", port);
        System.out.println("map-->" + map);
        return map;
    }
}

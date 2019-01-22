package com.jaxer.hellospringboot.component;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

/**
 * 错误请求处理
 * <p>
 * Created by jaxer on 2018/10/11
 */
@Component
public class MyErrorAttributes extends DefaultErrorAttributes {
    //这里返回的Map就是所有的返回结果
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Map<String, Object> map = super.getErrorAttributes(webRequest, includeStackTrace);
        map.put("company", "google.com");
        //从异常处理器获取信息
        webRequest.getAttribute("errorMsg", RequestAttributes.SCOPE_REQUEST);
        return map;
    }
}

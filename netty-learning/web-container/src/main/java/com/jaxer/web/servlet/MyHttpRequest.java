package com.jaxer.web.servlet;

import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.QueryStringDecoder;
import lombok.ToString;

import java.util.List;
import java.util.Map;

/**
 * Http请求
 * Created on 2020/6/25 16:53
 *
 * @author jaxer
 */
@ToString
public class MyHttpRequest {
    private HttpRequest request;

    public MyHttpRequest(HttpRequest request) {
        this.request = request;
    }

    public String getUri() {
        return request.uri();
    }

    public String getMethod() {
        return request.method().name();
    }

    public Map<String, List<String>> getParameters() {
        return new QueryStringDecoder(request.uri()).parameters();
    }
}

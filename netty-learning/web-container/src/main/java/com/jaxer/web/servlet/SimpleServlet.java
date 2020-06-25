package com.jaxer.web.servlet;

import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * 简单Servlet实现
 * Created on 2020/6/25 17:24
 *
 * @author jaxer
 */
public class SimpleServlet extends MyServlet {
    @Override
    public void doGet(MyHttpRequest request, MyHttpResponse response) {
        String uri = request.getUri();
        String method = request.getMethod();
        Map<String, List<String>> parameters = request.getParameters();

        JSONObject content = new JSONObject().fluentPut("uri", uri)
                .fluentPut("method", method)
                .fluentPut("parameters", parameters);
        response.write(content.toJSONString());
    }

    @Override
    public void doPost(MyHttpRequest request, MyHttpResponse response) {
        doGet(request, response);
    }
}

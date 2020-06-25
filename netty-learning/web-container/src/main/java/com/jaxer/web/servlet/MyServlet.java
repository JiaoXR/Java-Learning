package com.jaxer.web.servlet;

/**
 * 简单自定义Servlet规范
 * Created on 2020/6/25 17:10
 *
 * @author jaxer
 */
public abstract class MyServlet {
    public abstract void doGet(MyHttpRequest request, MyHttpResponse response);

    public abstract void doPost(MyHttpRequest request, MyHttpResponse response);
}

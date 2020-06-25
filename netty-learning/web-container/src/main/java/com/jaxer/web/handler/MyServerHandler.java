package com.jaxer.web.handler;

import com.jaxer.web.servlet.MyHttpRequest;
import com.jaxer.web.servlet.MyHttpResponse;
import com.jaxer.web.servlet.SimpleServlet;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.HttpRequest;

/**
 * 自定义Channel处理器
 * Created on 2020/6/25 17:20
 *
 * @author jaxer
 */
public class MyServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof HttpRequest) {
            System.out.println("收到HTTP请求，msg = " + msg);
            HttpRequest request = (HttpRequest) msg;
            SimpleServlet servlet = new SimpleServlet();
            servlet.doGet(new MyHttpRequest(request), new MyHttpResponse(request, ctx));
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}

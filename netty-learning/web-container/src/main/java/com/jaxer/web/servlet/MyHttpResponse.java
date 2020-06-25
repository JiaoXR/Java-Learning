package com.jaxer.web.servlet;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;
import lombok.ToString;

import java.nio.charset.StandardCharsets;

/**
 * Http响应
 * Created on 2020/6/25 17:10
 *
 * @author jaxer
 */
@ToString
public class MyHttpResponse {
    private HttpRequest request;

    private ChannelHandlerContext context;

    public MyHttpResponse(HttpRequest request, ChannelHandlerContext context) {
        this.request = request;
        this.context = context;
    }

    public void write(String content) {
        // 构造响应对象
        DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                HttpResponseStatus.OK,
                Unpooled.wrappedBuffer(content.getBytes(StandardCharsets.UTF_8)));

        // 设置响应头
        HttpHeaders headers = response.headers();
        headers.add(HttpHeaderNames.CONTENT_TYPE, "text/json");
        headers.add(HttpHeaderNames.CONTENT_LENGTH, response.content().readableBytes());
        headers.add(HttpHeaderNames.EXPIRES, 0);
        if (HttpUtil.isKeepAlive(request)) {
            headers.add(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
        }

        // 写入Channel
        // 注意这里写入的是HttpResponse
        context.channel().writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }
}

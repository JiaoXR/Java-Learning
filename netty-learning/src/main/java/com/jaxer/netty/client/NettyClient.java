package com.jaxer.netty.client;

import com.jaxer.common.CommonConstant;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * Netty客户端
 * Created on 2020/6/25 11:06
 *
 * @author jaxer
 */
public class NettyClient {
    public static void main(String[] args) throws InterruptedException {
        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup group = new NioEventLoopGroup();

        // 连接服务端，本机IP，8888端口
        ChannelFuture future = bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel channel) {
                        channel.pipeline().addLast(new StringEncoder());
                    }
                })
                .connect(CommonConstant.LOCAL_HOST_IP, CommonConstant.NETTY_PORT);

        // 获取Channel，发送数据
        Channel channel = future.channel();
        while (true) {
            String content = "当前时间：" + LocalDateTime.now();
            System.out.println(content);
            channel.writeAndFlush(content);
            TimeUnit.SECONDS.sleep(3);
        }
    }
}

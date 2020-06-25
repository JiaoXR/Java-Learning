package com.jaxer.netty.server;

import com.jaxer.common.CommonConstant;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;

/**
 * Netty服务端
 * Created on 2020/6/24 00:09
 *
 * @author jaxer
 */
public class NettyServer {
    public static void main(String[] args) throws InterruptedException {
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        NioEventLoopGroup parent = new NioEventLoopGroup();
        NioEventLoopGroup child = new NioEventLoopGroup();

        // Netty初始化
        serverBootstrap.group(parent, child)
                // 指定使用NIO，也可以使用BIO（OioServerSocketChannel 已废弃）
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel channel) {
                        // 解码器
                        channel.pipeline().addLast(new StringDecoder());

                        // 读数据
                        channel.pipeline().addLast(new SimpleChannelInboundHandler<String>() {
                            @Override
                            protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
                                // 打印接收的数据
                                System.out.println("收到数据：" + s);
                            }
                        });
                    }
                })
                // 绑定端口
                .bind(CommonConstant.NETTY_PORT).sync();

        System.out.println("Netty服务端启动，端口号：" + CommonConstant.NETTY_PORT);
    }
}

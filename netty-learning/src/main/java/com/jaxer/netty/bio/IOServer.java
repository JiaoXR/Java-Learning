package com.jaxer.netty.bio;

import com.jaxer.netty.common.CommonConstant;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;

/**
 * Socket服务端
 * Created on 2020/6/25 10:37
 *
 * @author jaxer
 */
public class IOServer {
    public static void main(String[] args) {
        new Thread(() -> {
            try {
                ServerSocket serverSocket = new ServerSocket(CommonConstant.HTTP_PORT);
                System.out.println("服务端启动，监听端口：" + CommonConstant.HTTP_PORT);

                while (true) {
                    Socket socket = serverSocket.accept();
                    InputStream inputStream = socket.getInputStream();
                    byte[] data = new byte[1024];
                    int length;
                    while ((length = inputStream.read(data)) != -1) {
                        System.out.println(LocalDateTime.now() + " --> 收到请求：" + new String(data, 0, length));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}

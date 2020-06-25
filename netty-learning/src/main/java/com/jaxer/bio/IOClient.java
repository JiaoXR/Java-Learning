package com.jaxer.bio;

import com.jaxer.common.CommonConstant;

import java.net.Socket;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * Socket客户端
 * Created on 2020/6/25 10:48
 *
 * @author jaxer
 */
public class IOClient {
    public static void main(String[] args) {
        new Thread(() -> {
            try {
                String format1 = "客户端启动，连接IP：%s，端口号：%s，准备发送数据......";
                System.out.println(String.format(format1, CommonConstant.LOCAL_HOST_IP, CommonConstant.HTTP_PORT));

                Socket socket = new Socket(CommonConstant.LOCAL_HOST_IP, CommonConstant.HTTP_PORT);
                String format2 = "现在时间：%s";
                while (true) {
                    String content = String.format(format2, LocalDateTime.now());
                    System.out.println(content);
                    socket.getOutputStream().write(content.getBytes());
                    TimeUnit.SECONDS.sleep(3);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}

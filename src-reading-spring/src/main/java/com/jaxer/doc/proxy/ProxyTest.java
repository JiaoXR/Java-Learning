package com.jaxer.doc.proxy;

import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created on 2020/5/16 14:24
 * 测试Proxy生成class文件
 *
 * @author jaxer
 */
public class ProxyTest {
    public static void main(String[] args) throws IOException {
        byte[] bytes = ProxyGenerator.generateProxyClass("UserServiceImpl", new Class[]{UserService.class});
        FileOutputStream outputStream = new FileOutputStream("UserServiceImpl.class");
        outputStream.write(bytes);
        outputStream.flush();
        outputStream.close();
    }
}

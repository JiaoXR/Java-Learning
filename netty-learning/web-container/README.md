# Netty实现简单Web容器

## 1. 效果演示

启动自定义Tomcat后，在浏览器端请求 localhost:8888，得到响应，示例效果如下：


## 2. 要点

- 自定义 HttpRequest、HttpResponse、Servlet规范

- 实现自定义Servlet

## 3. 主要Netty组件

- ChannelHandlerContext

- HttpRequest、HttpResponse

## 4. 遇到问题及解决方法

1. 浏览器发起请求后无响应

例如请求URI：http://localhost:8888/api/test?id=123    
发起请求后浏览器一直转圈，无响应。

原因：ChannelHandlerContext应写入的是HttpResponse，而非直接写入内容

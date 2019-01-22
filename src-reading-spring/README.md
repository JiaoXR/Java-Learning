#  Spring 文档&源码阅读





##  1.3  Bean Overview

- 元数据（metadata）格式
  - XML
  - Java 配置（`@Bean`）
- 实例化 Bean
  - 构造器
  - 静态工厂（`factory-method`）
  - 实例工厂（`factory-bean`, `factory-method`）

##  1.4  Depencencies

- Inner Beans: <bean\>  标签嵌套，封装内部类；

- Collections: <list\>, <set\>, <map\>, <props\> 标签，分别对应 List, Set, Map, Properties 类；

- p-namespace: p名称空间，p (\<property\>)，简化 XML 配置（setter 注入）；

- c-namespace: c名称空间，c (<constructor\>)，简化 XML 配置（构造器注入）；

- depends-on (<bean\> 标签): 显式强制初始化一个或多个bean；

- 惰性初始化
  - <bean\>: lazy-init
  - <beans\>: default-lazy-init

- autowiring
  - no
  - byName
  - byType
  - constructor

- Method Injection
  - ApplicationContextAware: 解决相互依赖的 bean 之间生命周期不同带来的问题【不可行】；
  - 方法注入：通过 CGLIB 动态生成子类，并重写需要的方法；
  - `@Lookup("methodName")`
  - 任意方法替换（了解）：实现 `MethodReplacer` 接口

##  1.5 Bean Scopes

  - singleton: 默认

    ![](https://docs.spring.io/spring/docs/5.1.3.RELEASE/spring-framework-reference/images/singleton.png)

  - prototype

    ![](https://docs.spring.io/spring/docs/5.1.3.RELEASE/spring-framework-reference/images/prototype.png)

  - request: a single HTTP request

    - 作用域是 HTTP 请求级别，请求完成处理后，bean 将被丢弃。

    - 示例代码：

      ```xml
      <!-- XML 配置 -->
      <bean id="loginAction" class="com.something.LoginAction" scope="request"/>
      ```

      ```java
      // 使用注解
      @RequestScope
      @Component
      public class LoginAction {
          // ...
      }
      ```

  - session: an HTTP Session

    - `@SessionScope`

    - 代理创建方式（`proxy-target-class`）

      - 基于 CGLIB 的类代理（CGLIB-based class proxy）
      - 基于 JDK 的接口代理（JDK interface-based proxies）

    - 当被注入到 scope 为 singleton 的 bean 时，需要使用代理，例如：

      ```xml
      <bean id="userPreferences" class="com.something.UserPreferences" scope="session">
          <aop:scoped-proxy proxy-target-class="false"/>
      </bean>
      ```

  - application: the lifecycle of a ServletContext

    - `@ApplicationScope`

  - websocket: the lifecycle of a WebSocket

##  1.6 Customizing the Nature of a Bean

- #### Lifecycle Callbacks

  - 推荐使用：`@PostConstruct`, `@PreDestroy`
  - 或 `init-method`, `destroy-method`
  - 不推荐使用：`InitializingBean` 和 `DisposableBean`

- 若配置多个初始化/销毁方法，则执行顺序如下：

    - 初始化
        1. `@PostConstruct`
        2. `InitializingBean#afterPropertiesSet()`
        3. `init-method`
    - 销毁
        1. `@PreDestroy`
        2. `DisposableBean#destroy()`
        3. `destroy-method`

- `context.registerShutdownHook()`




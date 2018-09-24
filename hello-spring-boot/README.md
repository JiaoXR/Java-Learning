#  Spring Boot 小结

###  配置文件值注入

- @ConfigurationProperties 与 @Value 注入方式比较

| -                                                            | @ConfigurationProperties           | @Value       |
| ------------------------------------------------------------ | ---------------------------------- | ------------ |
| 功能                                                         | 批量注入配置文件中的属性（prefix） | 只能单个指定 |
| 松散绑定（识别将 - 或 _ 后的字母转为大写）<br />例如 last-name 转为 lastName | 支持                               | 不支持       |
| SpEL                                                         | 不支持                             | 支持         |
| JSR303 数据校验                                              | 支持                               | 不支持       |
| 复杂类型（Map等）封装                                        | 支持                               | 不支持       |

###  引入配置文件

`@ImportResource`

- 配置文件占位符
  - ${random.uuid}
  - ${random.int}

###  Profile

- 多profile文件格式
- 多文档块

###  配置文件加载位置

1. file:./config/
2. file:./
3. classpath:/config/
4. classpath:./

优先级由高到低，高优先级会覆盖低优先级。

 ###  SpringBoot 自动配置（精髓）

1. SpringBoot 启动时会加载大量的自动配置类（`**AutoConfiguration`，自动配置类在一定条件下才生效）；
2. 判断需要的功能是否有对应的自动配置类，若已有则无需配置；
3. 给容器中自动配置类添加组件时，会从`**Properties` 类中获取属性，可以在配置文件指定相应的值。

可以启动 `debug=true` 属性，让控制台打印自动配置报告：

- Positive matches: 启用配置
- Negative matches: 未启用配置








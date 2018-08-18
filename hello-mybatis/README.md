#  MyBatis 小结

- MyBatis：半自动化的持久层框架。
- ORM: Object Ration Mapping，对象关系映射。
- SQL 与 Java 编码分离：SQL 由开发人员控制。

####  SqlSession

- sqlSessionFactory.openSession(): 不会自动提交，需要调用 `sqlSession.commit()` 手动提交

 * sqlSessionFactory.openSession(true): 自动提交；

###  CRUD

```xml
<!--
	MySQL支持自增主键，配置如下：
		useGeneratedKeys="true": 使用主键获取；
		keyProperty="id": 指定对应的主键属性
-->
<insert id="insertEmp" parameterType="Employee" useGeneratedKeys="true" keyProperty="id">
	INSERT INTO employee (name, age, gender, dept_id)
	VALUES (#{name}, #{age}, #{gender}, #{deptId})
</insert>
```

####  参数处理

- 单个参数

MyBatis 不做特殊处理。`#{param}` 形式取出参数即可

- 多个参数会被封装为一个 Map
  - key：param1, param2, ... paramN
  - value: 传入的参数值；

例如：

```xml
<select id="getByIdAndName" resultType="Employee">
	SELECT *
	FROM employee
	WHERE id = #{param1} AND name = #{param2}
</select>
```

或者在接口参数中指定，例如：

```java
Employee getByIdAndName(@Param("id") Integer id, @Param("name") String name);
```

- POJO

若多个参数与业务模型相匹配，可直接传入 POJO，并以 `#{param}` 形式取出参数。

- Map

若无对应的 POJO，可以传入 Map，并以 `#{param}` 形式取出参数。例如：

```java
Map<String, Object> map = new HashMap<>();
map.put("id", 1);
map.put("name", "Tom");
Employee employee1 = mapper.getByMap(map);
```

- DTO

多个参数经常使用时，可以封装为一个 DTO（Data Transfer Object）

####  一些特殊场景取值

- 场景一

```java
public Employee getEmp(@Param("id") Integer id, String name);
```

取值：id => #{id/param1}, name => #{param2}

- 场景二

```java
public Employee getEmp(Integer id, @Param("e") Employee emp);
```

取值：id => #{id}, name => #{e.name/param2.name}

- 场景三

如果参数是 Collection 或数组，会把传入的 list 或数组封装在 Map 中。

```java
public Employee getEmp(List<Integer> ids);
```

取值：取出第一个 id 值：#{list[0]}

####  取值方式比较

- `#{}` 【多数情况下使用】

以预编译的形式，将参数设置到 SQL 语句中，防止 SQL 注入；

`#{}` 更多用法：

规定参数的一些规则，javaType、jdbcType、resultMap、typeHandler、jdbcTypeName 等。

jdbcType 通常需要在某种特定的条件下被设置：

​	在数据为 null 的时候，有些数据库可能不能识别 MyBatis 对 null 的默认处理。比如 Oracle 会报错（jdbcType OTHER: 无效的类型；MyBatis 对 null 映射的是原生 JDBC 的 OTHER 类型）

- `${}`

取出的值直接拼装在 SQL 语句中，会有安全问题。










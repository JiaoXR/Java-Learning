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














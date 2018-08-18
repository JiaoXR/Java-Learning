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

###  取值方式比较

####  `#{}` 【多数情况下使用】

以预编译的形式，将参数设置到 SQL 语句中，防止 SQL 注入；

#####  `#{}` 更多用法：

规定参数的一些规则，javaType、jdbcType、resultMap、typeHandler、jdbcTypeName 等。

jdbcType 通常需要在某种特定的条件下被设置：

​	在数据为 null 的时候，有些数据库可能不能识别 MyBatis 对 null 的默认处理。比如 Oracle 会报错（jdbcType OTHER: 无效的类型；MyBatis 对 null 映射的是原生 JDBC 的 OTHER 类型）。

#####  解决方法

- `#{email, jdbcType=NULL}`
- 全局配置 jdbcTypeForNull

```xml
<setting name="jdbcTypeForNull" value="NULL"/>
```

####  `${}`

取出的值直接拼装在 SQL 语句中，会有安全问题。

###  自定义返回结果集 resultMap

示例代码：

```xml
<!--
	封装自定义结果集
-->
<resultMap id="MyEmp" type="com.jaxer.example.domain.Employee">
	<!--
		id 标签：指定主键列的封装规则，底层有优化
		column: 指定哪一列
		property: 指定对应的 JavaBean 属性
	-->
	<id column="id" property="id"/>
	<!-- 普通列封装规则 -->
	<result column="name" property="name"/>
    <!-- 其他未指定的列会自动封装，推荐把全部映射规则都封装好 -->
</resultMap>
```

封装级联属性，例如：

```xml
<!--
	封装级联属性，形式一：
-->
<resultMap id="MyEmpDept" type="com.jaxer.example.domain.Employee">
	<id column="id" property="id"/>
	<result column="name" property="name"/>
	<result column="gender" property="gender"/>
	<result column="dept_id" property="dept.id"/>
	<result column="dept_name" property="dept.deptName"/>
</resultMap>

<!--
	封装级联属性，形式二：
-->
<resultMap id="MyEmpDept2" type="com.jaxer.example.domain.Employee">
	<id column="id" property="id"/>
	<result column="name" property="name"/>
	<result column="gender" property="gender"/>
	<association property="dept" javaType="com.jaxer.example.domain.Department">
		<id column="dept_id" property="id"/>
		<result column="dept_name" property="deptName"/>
	</association>
</resultMap>
```












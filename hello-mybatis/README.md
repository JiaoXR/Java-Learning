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

    <!--
        封装级联属性，形式三：分步查询
    -->
    <resultMap id="MyEmpDept3" type="com.jaxer.example.domain.Employee">
        <id column="id" property="id"/>
        <result column="name" property="name"/>
        <result column="gender" property="gender"/>
		<!-- 将 dept_id 作为 getById 查询的参数，并将结果封装到 dept  -->
        <association property="dept" select="com.jaxer.example.dao.DepartmentMapper.getById" column="dept_id"/>
    </resultMap>

    <!-- 嵌套结果集的方式，使用 collection 标签定义关联的集合类型的属性封装规则 -->
    <resultMap id="MyDeptAndEmpList" type="com.jaxer.example.domain.Department">
        <id column="dept_id" property="id"/>
        <result column="dept_name" property="deptName"/>
        <collection property="employeeList" ofType="com.jaxer.example.domain.Employee">
            <id column="emp_id" property="id"/>
            <result column="emp_name" property="name"/>
            <result column="emp_age" property="age"/>
        </collection>
    </resultMap>

    <!-- 嵌套结果集，使用分步查询 -->
    <resultMap id="MyDeptAndEmpList2" type="com.jaxer.example.domain.Department">
        <id column="id" property="id"/>
        <result column="dept_name" property="deptName"/>
        <collection property="employeeList"
                    select="com.jaxer.example.dao.EmployeeMapper.getEmpListByDeptId"
                    column="id"/>
    </resultMap>
```

分步查询可使用懒加载，需要在全局配置中配置，如下：

```xml
<!--SQL查询懒加载:级联查询中，用到的时候再执行相应的SQL，可减少查询次数；推荐显式配置-->
<setting name="lazyLoadingEnabled" value="true"/>
<setting name="aggressiveLazyLoading" value="false"/>
```

###  动态 SQL

- if (配合 where 标签)

多条件查询，且有些可能为空时很适合。

- choose(when, otherwise)

分支选择，类似于 `switch...case` ，只会进入其中一个条件。

otherwise: 相当于 default

- trim(where, set)

  - prefix

  给拼装后的整个 SQL 加一个前缀；

  - prefixOverrides

  前缀覆盖：去掉整个字符串前面多余的字符；

  - suffix

  给拼装后的整个 SQL 加一个后缀；

  - suffixOverrides

  后缀覆盖：去掉整个字符串后面多余的字符；

- set

更新标签：\<set\>，能自动取出多余的逗号。

也可以使用 \<trim\> 标签。

- foreach
  - collection 指定要遍历的集合
  - index：索引，遍历list时是索引；遍历map时是map的key

###  内置参数

- _parameter: 代表整个参数
  - 单个参数：_parameter 就是这个参数
  - 多个参数：参数会被封装为一个map，_parameter代表这个map
- _databaseId: 如果配置了databaseIdProvider标签
  - _databaseId就是代表当前数据库的别名

示例代码：

```xaml
	<select id="listEmpBy" resultType="employee">
        <!-- 例如，这里可以根据不同的数据库执行不同的SQL -->
        <if test="_databaseId=='mysql'">
            SELECT *
            FROM employee
            <if test="_parameter!=null">
                WHERE id = #{id}
            </if>
        </if>
    </select>
```

- bind 标签【了解】

绑定参数，示例代码：

```xml
	<select id="listEmpBy" resultType="employee">
        <bind name="_lastName" value="'%'+lastName+'%'" />
        <if test="_databaseId=='mysql'">
            SELECT *
            FROM employee
            WHERE last_name LIKE #{_lastName}
        </if>
    </select>
```

- sql 标签

用于抽取可复用的SQL片段，比如经常要查询/插入的列名。示例代码：

 ```xml
    <sql id="selectColumn">
        id, name, age, gender, dept_id
    </sql>
 ```

使用 \<include\> 标签引用，且 include 标签内可以新增自定义属性。

##  缓存机制

MyBatis 默认定义了两级缓存：一级缓存和二级缓存。

###  一级缓存（本地缓存）

与数据库同一次会话期间查询到的数据会放在本地缓存中，以后如果需要获取相同的数据，直接从缓存中取。

默认情况下，只有一级缓存（SqlSession 级别的缓存，也称本地缓存）是一直开启的。

####  缓存失效

1. SqlSession 发生改变；
2. SqlSession 不变，查询条件改变（当前一级缓存中还没有这个数据）；
3. SqlSession 不变，但两次查询之间执行过增删改操作（这些操作可能对当前数据有影响）；
4. SqlSession 不变，但两次查询之间手动清空了缓存。

###  二级缓存（全局缓存）

基于 namespace 级别的缓存（全局缓存），每个 namespace 对应一个二级缓存。为提高扩展性，MyBatis 定义了缓存接口 Cache，可自定义二级缓存。

使用步骤：

1. 开启全局二级缓存配置：`<setting name="cacheEnabled" value="true"/>` ；
2. `**mapper.xml` 文件中配置使用二级缓存：`<cache></cache>` ；
3. POJO 需要实现序列化接口；

示例代码：

```xml
    <!-- 开启二级缓存
            eviction：缓存回收策略
                LRU：最近最少使用，移除最长时间不被使用的对象
                FIFO：先进先出
                SOFT：软引用，移除基于垃圾回收器状态和软引用规则的对象；
                WEAK：弱引用，更积极地移除基于垃圾收集器状态和弱引用规则的对象；
                默认是LRU
            flushInterval：缓存刷新间隔
                默认不清空，单位：毫秒
            readOnly：是否只读
                true：只读，MyBatis 认为所有从缓存中获取数据的操作都是只读操作；
                    MyBatis 为了提高速度，直接将数据在缓存中的引用交给用户。
                    不安全，速度快。
                false：非只读
                    MyBatis 会使用序列化&反序列化的技术克隆一份新的数据；
                    安全，速度稍慢，默认。
            size：缓存存放多少元素；
            type：指定自定义缓存的全类名；可实现 Cache 接口自定义。
    -->
    <cache eviction="LRU" flushInterval="50000" readOnly="false" size="1024"/>
```

> 注意：
>
> - 查询数据默认会被先放到一级缓存中；
> - 只有会话提交或关闭之后，数据才会从一级缓存转移到二级缓存中。

###  缓存相关属性

- cacheEnabled

若设置关闭 `<setting name="cacheEnabled" value="false"/>`，关闭的是二级缓存，一级缓存可用。

- useCache

 \<select\> 标签的 useCache 属性，若设置为 false，则关闭的是二级缓存，一级缓存不影响。

- flushCache

增删改标签的 flushCache 属性，若设置为 true，一级和二级缓存会清空。

- sqlSession.clearCache

只清除当前 Session 的一级缓存。

- localCacheScope

本地缓存作用域，默认是一级缓存 Session，当前会话的所有数据保存在会话缓存中。




package com.jaxer.example;

import java.sql.*;
import java.text.SimpleDateFormat;

/**
 * 代码千万行，注释第一行。
 * 测试 JDBC 连接 MySQL
 * <p>
 * Created by jaxer on 2019-07-04
 */
public class JdbcTest {
	private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/mybatis?useSSL=false&serverTimezone=Asia/Shanghai";
	// MySQL 8.0 以上版本 - JDBC 驱动名及数据库 URL
//	private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
//	private static final String DB_URL = "jdbc:mysql://localhost:3306/mybatis?useSSL=false&serverTimezone=UTC";

	private static final String JDBC_USERNAME = "root";
	private static final String JDBC_PASSWORD = "hello";

	public static void main(String[] args) {
		Connection connection = null;
		Statement statement = null;
		try {
			// 注册JDBC驱动
			Class.forName(JDBC_DRIVER);
			// 打开连接
			System.out.println("连接数据库..");
			connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
			// 执行查询
			System.out.println("执行查询...");
			statement = connection.createStatement();
			String sql = "SELECT * FROM department";
			ResultSet resultSet = statement.executeQuery(sql);
			// 封装结果集
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
//				Date createTime = new Date(resultSet.getTimestamp("create_time").getTime());
				Date date = new Date(resultSet.getTimestamp("create_time").getTime());
				System.out.println("id: " + id + ", name: " + name + ", createTime: " + format(date));
			}
			// 关闭资源
			resultSet.close();
			statement.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("查询结束.");
	}

	private static String format(Date date) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return simpleDateFormat.format(date);
	}
}

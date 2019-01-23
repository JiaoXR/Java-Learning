package com.jaxer.example.source.sql;

import com.jaxer.example.domain.Student;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.source.RichSourceFunction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static com.jaxer.example.constant.Constant.*;

/**
 * Created by jiaoxiangru on 3:53 PM 2019/1/22
 * 自定义数据源--MySQL
 */
public class SourceMySQL extends RichSourceFunction<Student> {

    private PreparedStatement statement;
    private Connection connection;

    /**
     * open() 方法中建立连接，这样不用每次 invoke 的时候都要建立连接和释放连接。
     */
    @Override
    public void open(Configuration parameters) throws Exception {
        super.open(parameters);
        connection = getConnection();
        String sql = "SELECT * FROM student;";
        statement = this.connection.prepareStatement(sql);
    }

    /**
     * 程序执行完毕就可以进行，关闭连接和释放资源的动作了
     */
    @Override
    public void close() throws Exception {
        super.close();
        // 关闭连接和释放资源
        if (connection != null) {
            connection.close();
        }
        if (statement != null) {
            statement.close();
        }
    }

    /**
     * DataStream 调用一次 run() 方法用来获取数据
     */
    @Override
    public void run(SourceContext<Student> context) throws Exception {
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Student student = new Student(
                    resultSet.getInt("id"),
                    resultSet.getString("name").trim(),
                    resultSet.getString("gender").trim(),
                    resultSet.getInt("age")
            );
            context.collect(student);
        }
    }

    @Override
    public void cancel() {

    }

    private static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(MYSQL_URL, MYSQL_USER, MYSQL_PASSWORD);
        } catch (Exception e) {
            System.out.println("---------- mysql get connection has exception , msg = " + e.getMessage());
        }
        return connection;
    }
}

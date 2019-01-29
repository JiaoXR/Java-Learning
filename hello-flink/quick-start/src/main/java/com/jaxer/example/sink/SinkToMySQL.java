package com.jaxer.example.sink;

import com.jaxer.example.domain.Student;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.jaxer.example.constant.Constant.*;

/**
 * 自定义 Sink 函数
 * Created by jiaoxiangru on 1:44 PM 2019/1/23
 */
@Slf4j
public class SinkToMySQL extends RichSinkFunction<Student> {

    private PreparedStatement statement;
    private Connection connection;

    @Override
    public void open(Configuration parameters) throws Exception {
        super.open(parameters);
        connection = getConnection();
        String sql = "INSERT INTO student(name, gender, age) VALUES (?, ?, ?);";
        statement = this.connection.prepareStatement(sql);
    }

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

    @Override
    public void invoke(Student student, Context context) throws SQLException {
        // 组装数据，执行插入操作
        statement.setString(1, student.getName());
        statement.setString(2, student.getGender());
        statement.setInt(3, student.getAge());
        statement.execute();
    }

    private static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(MYSQL_URL, MYSQL_USER, MYSQL_PASSWORD);
        } catch (Exception e) {
            log.info("---------- mysql get connection has exception ,msg = {}", e.getMessage());
        }
        return connection;
    }
}

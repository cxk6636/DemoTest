package com.mysql;

import java.sql.*;

public class MySQLConnectionDemo {
    public static void main(String[] args) throws SQLException {

        String url = "jdbc:mysql://localhost:3306/mydatabase"; // 数据库 URL
        String username = "myuser"; // 数据库用户名
        String password = "mypassword"; // 数据库密码

        Connection conn = DriverManager.getConnection(url, username, password); // 获取数据库连接

        // 处理数据库操作
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM mytable");
        while (rs.next()) {
            System.out.println(rs.getString("column1"));
        }
        conn.close(); // 关闭数据库连接
    }
}
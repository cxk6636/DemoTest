package com.mysql;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MySQLConnectionDemo {

    static class MyRun implements Runnable {
        @Override
        public void run() {
            String url = "jdbc:mysql://127.0.0.1:3306/mydatabase"; // 数据库 URL
            String username = "root"; // 数据库用户名
            String password = "123456"; // 数据库密码

            Connection conn = null; // 获取数据库连接
            try {
                conn = DriverManager.getConnection(url, username, password);
                while(true) {
                    Statement stmt = conn.createStatement();
                    boolean b = RandomUtils.nextBoolean();
                    if(b) {
                        String s = RandomStringUtils.randomAlphabetic(11);
                        stmt.execute("insert into mytable(`name`) values('" + s + "')");
                    } else {
                        stmt.execute("select * from  mytable limit 100");
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) throws SQLException, InterruptedException {
        for(int i = 0; i < 50; i++) {
            Thread thread = new Thread(new MyRun());
            String name = thread.getName();
            System.out.println("线程：" + name);
            thread.start();
        }

    }

    public void m() {
        Map<String, Map<String, Integer>> m = new HashMap<>();
        if(!m.containsKey("user")) {
            m.put("user", new HashMap<>());
        }
        if(!m.get("user").containsKey("total")) {
            m.get("user").put("total", 0);
        }
        m.get("user").put("total", m.get("user").get("total") + 1);


        String Command = "ss";
        if(Command.equals("aa")) {

        }
    }
}
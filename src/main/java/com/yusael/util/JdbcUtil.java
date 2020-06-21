package com.yusael.util;

import javax.sql.DataSource;
import java.sql.*;

// Java Data Base Connect
public class JdbcUtil { // 连接数据库

    public static DataSource ds = null;
    //    static {
//        try {
//            //1.加载配置文件
//            Properties p = new Properties();
//            FileInputStream in = new FileInputStream("/db.properties");
//            // InputStream in = JdbcUtil.class.getClassLoader().getResourceAsStream("db.properties");
//            p.load(in);
//            //ds = BasicDataSourceFactory.createDataSource(p);
//            ds = DruidDataSourceFactory.createDataSource(p);
//        }catch(Exception e) {
//            e.printStackTrace();
//        }
//    }
    public static Connection getConn() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/sxn210224?useSSL=false";
            String user = "root";
            String password = "1234";
            // 2.连接数据
            // return ds.getConnection();
            connection = DriverManager.getConnection(url, user, password);
            return connection;
        } catch (Exception e) {
            System.out.println("报错了！");
            e.printStackTrace();
        }
        return connection;
    }

    public static void close(Connection conn, Statement st, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {

                e.printStackTrace();
            }
        }
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

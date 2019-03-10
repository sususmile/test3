package com.okliu.list.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * <p>Title: DBUtils.java</p>
 * <p>Description: 数据库连接工具：
 * jdbc操作，分为5步：贾琏欲执事：
 *  1、加载数据库驱动
 *  2、连接数据库
 *  3、数据库语句
 *  4、执行数据库操作
 *  5、释放资源</p>
 * @author liuhaolie
 * @date 2019-02-21 09:57
 * @version 1.0
 */
public class DBUtils {

    private static String DRIVER;
    private static String URL;
    private static String USERNAME;
    private static String PASSWORD;

    static {//静态代码块，会在类首次加载时执行
        try {
            //加载配置文件，获取对应的数据
            Properties prop = load("db.properties");
            DRIVER = prop.getProperty("jdbc.driver");
            URL = prop.getProperty("jdbc.url");
            USERNAME = prop.getProperty("jdbc.username");
            PASSWORD = prop.getProperty("jdbc.password");
            //执行数据库驱动（加载）
            Class.forName(DRIVER);//反射技术
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
         * 加载配置文件.properties
     * @param path
     * @return
     * @throws IOException
     */
    private static Properties load(String path) throws IOException {
        Properties prop = new Properties();
        prop.load(DBUtils.class.getResourceAsStream(path));
        return prop;
    }

    /**
         *  获取数据库连接对象
     */
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
         * 关闭数据库的相关对象
     * @param conn
     * @param stmt
     * @param rst
     */
    public static void close(Connection conn,
            Statement stmt,ResultSet rst) {
        if(rst != null) {
            try {
                rst.close();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
        if(stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        if(conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws SQLException {
        System.out.println("Connection State："+(getConnection().isClosed()?"OFF":"ON"));
    }
    
}

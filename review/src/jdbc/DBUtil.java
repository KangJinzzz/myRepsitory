package jdbc;


import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.*;

public class DBUtil {

//    private static String URL = "jdbc:mysql://101.200.144.55:3306/review?useUnicode=true&characterEncoding=utf-8" ;

//    private static String URL = "jdbc:mysql://127.0.0.1:3306/review?characterEncoding=utf8&useSSL=true";
    private static String URL = "jdbc:mysql://localhost:3306/review";
    private static String USERNAME = "root";
    private static String PASSWORD = "kjw123";

    private static volatile DataSource DATASOURCE = null;

    /**
     * 单例模式获取 DataSource 对象
     */
    private static DataSource getDataSource() {
        if (DATASOURCE == null) {
            synchronized (DBUtil.class) {
                if (DATASOURCE == null) {
                    DATASOURCE = new MysqlDataSource();
                    ((MysqlDataSource)DATASOURCE).setURL(URL);
                    ((MysqlDataSource)DATASOURCE).setUser(USERNAME);
                    ((MysqlDataSource)DATASOURCE).setPassword(PASSWORD);
                }
            }
        }
        return DATASOURCE;
    }

    public static Connection getConnection() {
        try {
            return getDataSource().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("连接数据库失败！");
        }
    }

    public static Connection getConnection1() {
        Connection connection = null;
        try {
            //加载JDBC驱动程序
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("数据库连接失败！");
        }
        return connection;
    }

    public static void close(Connection connection, Statement statement, ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println(getConnection());
    }
}

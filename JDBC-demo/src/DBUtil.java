import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.*;

public class DBUtil {

    private static String URL = "jdbc:mysql://localhost:3306/java12";
    private static String USERNAME = "root";
    private static String PASSWORD = "kjw123";

    private static DataSource DATASOURCE = new MysqlDataSource();
    static {
        ((MysqlDataSource)DATASOURCE).setUrl(URL);
        ((MysqlDataSource)DATASOURCE).setUser(USERNAME);
        ((MysqlDataSource)DATASOURCE).setPassword(PASSWORD);
    }



    public static Connection getConnection1() {
        Connection connection = null;
        try {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            } finally {
                connection.close();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("数据库连接失败！");
        }
        return connection;
    }

    public static Connection getConnection2() {
        try {
            Connection connection = DATASOURCE.getConnection();
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("数据库连接失败！");
        }

    }

    public static void close(Connection connection, Statement statement, ResultSet resultSet) {
        try {
            if(connection != null) {
                connection.close();
            }
            if(statement != null) {
                statement.close();
            }
            if(resultSet != null) {
                resultSet.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public static void main(String[] args) {
//        Connection connection = getConnection1();
//        System.out.println(connection);
//    }
}

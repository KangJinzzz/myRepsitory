import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.*;

public class DBUtil {

    private static String URL = "jdbc:mysql://localhost:3306/review";
    private static String USERNAME = "root";
    private static String PASSWORD = "kjw123";

    private static DataSource DATASOURCE = new MysqlDataSource();
    static {
        ((MysqlDataSource)DATASOURCE).setURL(URL);
        ((MysqlDataSource)DATASOURCE).setUser(USERNAME);
        ((MysqlDataSource)DATASOURCE).setPassword(PASSWORD);
    }


    private static DataSource DATASOURCE2;

    public DBUtil() {

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

    public static Connection getConnection2() {
        try {
            Connection connection = DATASOURCE.getConnection();
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("数据库连接失败！");
        }

    }


    /**
     * 单例的方式
     */
    public static DataSource getDataSource() {
        if(DATASOURCE2 == null) {
            DATASOURCE2 = new MysqlDataSource();
            ((MysqlDataSource)DATASOURCE2).setURL(URL);
            ((MysqlDataSource)DATASOURCE2).setUser(USERNAME);
            ((MysqlDataSource)DATASOURCE2).setPassword(PASSWORD);
        }
        return DATASOURCE2;
    }

    public static void close(Connection connection, Statement statement, ResultSet resultSet) {
        try {
            if(resultSet != null)
                resultSet.close();
            if(statement != null)
                statement.close();
            if(connection != null)
                connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println(getConnection1());
        System.out.println(getConnection2());
        System.out.println(getDataSource());
    }
}

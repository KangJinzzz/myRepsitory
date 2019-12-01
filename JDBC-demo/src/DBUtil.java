import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

    private static String URL = "jdbc:mysql://localhost:3306/java12";
    private static String USERNAME = "root";
    private static String PASSWORD = "kjw123";



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

//    public static void main(String[] args) {
//        Connection connection = getConnection1();
//        System.out.println(connection);
//    }
}

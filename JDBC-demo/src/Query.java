import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Query {
    public static void main(String[] args) {
        selectByid();
    }

    public static void selectByid() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DBUtil.getConnection1();
            statement = connection.createStatement();
            String sql1 = "select id, name, chinese, math, english from exam_result;";
            resultSet = statement.executeQuery(sql1);
            while(resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                BigDecimal chinese = resultSet.getBigDecimal("chinese");
                BigDecimal math = resultSet.getBigDecimal("math");
                BigDecimal english = resultSet.getBigDecimal("english");
                System.out.printf("id = %d, name = %s, chinese = %s, math = %s, english = %s", id, name, chinese, math, english);
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection, statement,resultSet);
        }
    }

    public static void Insert() {
        String sql2 = "insert into exam_result(id, name, chinese, math, english) values (8, '徐凤年', 99, 97, 66);";

        Connection connection = null;
        Statement statement = null;
        try {
            connection = DBUtil.getConnection2();
            statement = connection.createStatement();
            int num = statement.executeUpdate(sql2);
            if(num > 0) {
                 System.out.println("插入成功！");
            } else {
                 System.out.println("插入失败！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection, statement,null);
        }

    }
}

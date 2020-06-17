package jdbc;

import java.math.BigDecimal;
import java.sql.*;

public class Query {
    
    public static void main(String[] args) {
        selectById();
    }

    public static void selectById() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String sql = "select * from exam_result;";

        try {
            connection = DBUtil.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                BigDecimal math = resultSet.getBigDecimal("math");
                BigDecimal chinese = resultSet.getBigDecimal("chinese");
                BigDecimal english = resultSet.getBigDecimal("english");
                System.out.printf("id = %d  name = %s  math = %s  chinese = %s  english = %s\n", id, name, math, chinese, english);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection, statement, resultSet);
        }
    }
}

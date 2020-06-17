package jdbc;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Insert {
    public static void main(String[] args) {
        insert();
    }

    public static void insert() {
        Connection connection = null;
        PreparedStatement statement = null;
        String sql = "insert into exam_result (name, math, chinese, english)" +
                "values ('徐凤年', ?, ?, ?);";

        try {
            connection = DBUtil.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setBigDecimal(1, BigDecimal.valueOf(90));
            statement.setBigDecimal(2, BigDecimal.valueOf(90));
            statement.setBigDecimal(3, BigDecimal.valueOf(88));

            int num = statement.executeUpdate();
            if (num > 0) {
                System.out.println("插入成功！");
            } else {
                System.out.println("插入失败！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection, statement, null);
        }

    }
}

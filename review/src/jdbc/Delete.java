package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Delete {

    public static void main(String[] args) {
        delete(4);
    }

    public static void delete(int dropId) {
        String sql = "delete from exam_result where id = ?";

        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBUtil.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, dropId);

            int num = statement.executeUpdate();
            if(num > 0) {
                System.out.println("删除成功！");
            } else {
                System.out.println("删除失败！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection, statement,null);
        }
    }
}
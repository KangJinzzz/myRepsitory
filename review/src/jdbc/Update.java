package jdbc;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Update {

    public static void main(String[] args) {
        update();
    }

    public static void update() {
        String sql = "update exam_result set math = 95 where id = 2";

        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBUtil.getConnection();
            statement = connection.prepareStatement(sql);
            int num = statement.executeUpdate();
            if(num > 0) {
                System.out.println("修改成功！");
            } else {
                System.out.println("修改失败！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection, statement,null);
        }
    }
}

package JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CrudOperation {

    public Student getStudentById(int id) {
        Connection conn = JdbcConfig.getConnectionObject();

        if(conn == null) {
            System.out.println("Failed to establish database connection.");
            return null;
        }

        String query = "SELECT * FROM student WHERE id=?";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next()) {
                return new Student(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getDouble(4)
                );
            }


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;

    }


}

package JDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CrudOperation {

    public Student getStudentById(int id) {
        Connection conn = JdbcConfig.getConnectionObject();

        if(conn == null) {
            System.out.println("Failed to establish database connection!");
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


    public List<Student> getStudentList() {
        Connection connection = JdbcConfig.getConnectionObject();
        List<Student> studentList = new ArrayList<>();

        if(connection == null) {
            System.out.println("Failed to establish database connection!");
            return studentList;
        }

        String query = "SELECT * FROM student;";

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while(rs.next()) {
                studentList.add(
                        new Student(
                                rs.getInt(1),
                                rs.getString(2),
                                rs.getInt(3),
                                rs.getDouble(4)
                        )
                );
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return studentList;
    }


    public boolean addNewStudent(Student student) {
        Connection connection = JdbcConfig.getConnectionObject();
        if(connection == null) {
            System.out.println("Failed to establish database connection!");
        }

        String query = "INSERT INTO student(name, age, marks) VALUES(?, ?, ?);";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, student.getName());
            preparedStatement.setInt(2, student.getAge());
            preparedStatement.setDouble(3, student.getMarks());

            int affectedRows = preparedStatement.executeUpdate();

            return affectedRows > 0;


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return false;
    }




}

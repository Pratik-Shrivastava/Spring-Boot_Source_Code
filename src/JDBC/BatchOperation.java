package JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class BatchOperation {

    public void addStudentList(List<Student> studentList) {
        Connection connection = JdbcConfig.getConnectionObject();
        if(connection == null) {
            System.out.println("Failed to establish database connection!");
            return;
        }

        String query = "INSERT INTO student(name, age, marks) VALUES (?, ?, ?);";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            for(Student student: studentList) {
                preparedStatement.setString(1, student.getName());
                preparedStatement.setInt(2, student.getAge());
                preparedStatement.setDouble(3, student.getMarks());

                preparedStatement.addBatch();
            }

            int[] affectedRows = preparedStatement.executeBatch();
            for(int i=0; i < affectedRows.length; i++) {
                if(affectedRows[i] == 0) {
                    System.out.println("Failed to insert " + i+1 + "entry!");
                }
            }
            System.out.println("List of students inserted successfully!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }
}

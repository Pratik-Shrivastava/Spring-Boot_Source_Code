package JDBC;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Connection connection = JdbcConfig.getConnectionObject();
        Scanner sc = new Scanner(System.in);
        CrudOperation crudOperation = new CrudOperation();
        BatchOperation batchOperation = new BatchOperation();

        do {
            System.out.println("1. Fetch student using id.");
            System.out.println("2. Fetch all students.");
            System.out.println("3. Insert an entry in student table.");
            System.out.println("4. Delete an entry in student table.");
            System.out.println("5. Add student list in student table.");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter the student id: ");
                    int id = sc.nextInt();
                    Student student1 = crudOperation.getStudentById(connection, id);

                    if(student1 == null) {
                        System.out.println("Student does not exist with this id!");
                    } else {
                        System.out.println(student1);
                    }

                    break;

                case 2:

                    List<Student> studentList = crudOperation.getStudentList(connection);

                    if (studentList == null || studentList.isEmpty()) {
                        System.out.println("Student table is empty!");
                    } else {
                        System.out.println(studentList);
                    }

                    break;

                case 3:

                    Student newStudent = new Student();

                    System.out.print("Enter the name of the student: ");
                    newStudent.setName(sc.next());

                    System.out.print("Enter the age of the student: ");
                    newStudent.setAge(sc.nextInt());

                    System.out.print("Enter the marks of the student: ");
                    newStudent.setMarks(sc.nextDouble());

                    boolean added = crudOperation.addNewStudent(connection, newStudent);

                    if (added) {
                        System.out.println("New student added successfully!");
                    } else {
                        System.out.println("Failed to add new student!");
                    }

                    break;

                case 4:

                    System.out.print("Enter the id to be deleted: ");
                    int deleteId = sc.nextInt();

                    boolean deleted = crudOperation.deleteStudentById(connection, deleteId);

                    if(deleted) {
                        System.out.println("Student entry deleted successfully!");
                    } else {
                        System.out.println("Failed to delete student entry!");
                    }

                    break;

                case 5:
                    List<Student> studentList1 = new ArrayList<>();
                    do {
                        Student student = new Student();

                        System.out.print("Enter the name of the student: ");
                        student.setName(sc.next());

                        System.out.print("Enter the age of the student: ");
                        student.setAge(sc.nextInt());

                        System.out.print("Enter the marks of the student: ");
                        student.setMarks(sc.nextDouble());

                        studentList1.add(student);

                        System.out.print("Add more data (Y/N): ");
                        if(sc.next().toUpperCase().equals("N")) {
                            break;
                        }
                    } while (true);
                    batchOperation.addStudentList(connection, studentList1);
                    break;
                default:
                    System.out.print("Please entry a valid choice!");
                    continue;

            }

            System.out.print("Do you want to continue? (Y / N): ");
            if(sc.next().toUpperCase().equals("N")) {
                break;
            }

        } while (true);



    }

}

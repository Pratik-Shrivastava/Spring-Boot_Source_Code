package JDBC;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        CrudOperation crudOperation = new CrudOperation();

        while(true) {
            System.out.println("1. Fetch student using id.");
            System.out.println("2. Fetch student list.");
            System.out.println("3. Insert an entry in student table.");
            System.out.println("4. Delete an entry in student table.");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter the id: ");
                    int id = sc.nextInt();
                    Student student = crudOperation.getStudentById(id);

                    if(student == null) {
                        System.out.println("No student exist with id " + id);
                    } else {
                        System.out.println(student);
                    }
                    break;

                case 2:
                case 3:
                case 4:
                    break;

            }

            System.out.print("Do you want to continue? (Y / N): ");
            if(sc.next().toUpperCase().equals("N")) {
                break;
            }
        }



    }
}

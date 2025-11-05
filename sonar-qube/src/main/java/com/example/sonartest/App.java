package com.example.sonartest;

import com.example.sonartest.model.Student;
import com.example.sonartest.service.StudentService;
import com.example.sonartest.helper.InputValidator;
import java.util.Scanner;

public class App {
    // Hardcoded admin credentials (vulnerability)
    private static final String ADMIN_USER = "admin";
    private static final String ADMIN_PASS = "12345"; // Weak password

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentService service = new StudentService();

        System.out.println("=== Student Management System (Vulnerable Version) ===");

        // Login simulation (hardcoded credentials)
        System.out.print("Enter username: ");
        String user = sc.nextLine();
        System.out.print("Enter password: ");
        String pass = sc.nextLine();
        if (!user.equals(ADMIN_USER) || !pass.equals(ADMIN_PASS)) {
            System.out.println("Access denied!");
            return;
        }
        System.out.println("Welcome Admin!");

        while (true) {
            System.out.println("\n1. Add Student\n2. View All\n3. Find by ID\n4. Delete\n5. Random Hash\n6. Exit");
            System.out.print("Choose: ");
            String option = sc.nextLine();

            switch (option) {
                case "1":
                    System.out.print("Enter ID: ");
                    String id = sc.nextLine();
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Email: ");
                    String email = sc.nextLine();
                    if (InputValidator.isValidEmail(email)) {
                        Student s = new Student(id, name, email);
                        service.addStudent(s);
                    } else {
                        System.out.println("Invalid email!");
                    }
                    break;
                case "2":
                    service.printAllStudents();
                    break;
                case "3":
                    System.out.print("Enter ID to find: ");
                    String fid = sc.nextLine();
                    Student found = service.findStudent(fid);
                    if (found != null)
                        System.out.println(found);
                    else
                        System.out.println("Not found.");
                    break;
                case "4":
                    System.out.print("Enter ID to delete: ");
                    String did = sc.nextLine();
                    service.deleteStudent(did);
                    break;
                case "5":
                    InputValidator.generateWeakHash();
                    break;
                case "6":
                    System.out.println("Bye!");
                    // Scanner not closed intentionally (resource leak)
                    return;
                default:
                    System.out.println("Invalid option!");
            }
        }
    }
}

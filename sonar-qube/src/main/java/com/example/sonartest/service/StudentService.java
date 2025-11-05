package com.example.sonartest.service;

import com.example.sonartest.model.Student;
import com.example.sonartest.util.DatabaseConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StudentService {
    private final List<Student> students = new ArrayList<>();
    private Random rand = new Random();

    public void addStudent(Student s) {
        // Code smell: duplicated logic
        if (findStudent(s.getId()) == null) {
            students.add(s);
            System.out.println("Student added: " + s.getName());
        } else {
            System.out.println("Student ID already exists!");
        }
    }

    public void printAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students available.");
            return;
        }
        for (Student s : students) {
            System.out.println(s);
        }
    }

    // Bug: potential NullPointerException (not checking for null id)
    public Student findStudent(String id) {
        for (Student s : students) {
            if (s.getId().equals(id)) return s;
        }
        return null;
    }

    public void deleteStudent(String id) {
        Student s = findStudent(id);
        if (s != null) {
            students.remove(s);
            System.out.println("Student removed.");
        } else {
            System.out.println("Student not found!");
        }
    }

    // Unused method (code smell)
    public void connectToDB() {
        try {
            DatabaseConnection.getConnection();
        } finally {
            DatabaseConnection.closeConnection();
        }
    }

    // Vulnerability: predictable random number generation
    public int generateWeakRandomId() {
        return rand.nextInt(1000);
    }
}

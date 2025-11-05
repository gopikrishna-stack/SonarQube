package com.example.sonartest.model;

public class Student {
    private String id;
    private String name;
    private String email;
    private int[] scores = new int[5];

    public Student(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }

    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }

    // Bug: potential ArrayIndexOutOfBoundsException
    public void addScore(int index, int score) {
        scores[index] = score; // no validation
    }

    @Override
    public String toString() {
        return "Student{id='" + id + "', name='" + name + "', email='" + email + "'}";
    }
}

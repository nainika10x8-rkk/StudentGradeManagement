//shri radha
package com.studentmanagement.model;

public class Marks {

    private int markId;
    private int studentId;
    private int courseId;
    private int marks;
    private String grade;

    public Marks(int markId, int studentId, int courseId, int marks) {
        this.markId = markId;
        this.studentId = studentId;
        this.courseId = courseId;
        this.marks = marks;
        this.grade = calculateGrade(marks);
    }

    public int getMarkId() {
        return markId;
    }

    public int getStudentId() {
        return studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public int getMarks() {
        return marks;
    }

    public String getGrade() {
        return grade;
    }

    public void setMarks(int marks) {
        this.marks = marks;
        this.grade = calculateGrade(marks);
    }

    private String calculateGrade(int marks) {

        if (marks >= 90) {
            return "A+";
        } else if (marks >= 80) {
            return "A";
        } else if (marks >= 70) {
            return "B";
        } else if (marks >= 60) {
            return "C";
        } else if (marks >= 50) {
            return "D";
        } else if (marks >= 40) {
            return "E";
        } else {
            return "F";
        }
    }

    public void displayMarks() {
        System.out.println("Mark ID    : " + markId);
        System.out.println("Student ID : " + studentId);
        System.out.println("Course ID  : " + courseId);
        System.out.println("Marks      : " + marks);
        System.out.println("Grade      : " + grade);
    }
}

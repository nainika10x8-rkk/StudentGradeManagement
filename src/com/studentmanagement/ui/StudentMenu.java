//shri radha 
package com.studentmanagement.ui;

import com.studentmanagement.dao.CourseDAO;
import com.studentmanagement.dao.MarksDAO;
import com.studentmanagement.model.Course;
import com.studentmanagement.model.Marks;
import com.studentmanagement.model.Student;
import com.studentmanagement.service.ReportService;
import com.studentmanagement.service.StudentService;
import java.util.List;
import java.util.Scanner;

public class StudentMenu {

    private final Scanner scanner;
    private final StudentService studentService;
    private final CourseDAO courseDAO;
    private final MarksDAO marksDAO;
    private final ReportService reportService;

    public StudentMenu(Scanner scanner) {
        this.scanner = scanner;
        studentService = new StudentService();
        courseDAO = new CourseDAO();
        marksDAO = new MarksDAO();
        reportService = new ReportService();
    }

    public void showMenu(int studentId) {

        Student student = studentService.getStudentById(studentId);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        int choice;

        do {
            System.out.println();
            System.out.println("=================================");
            System.out.println("         STUDENT PORTAL          ");
            System.out.println("=================================");
            System.out.println("Welcome, " + student.getName());
            System.out.println();
            System.out.println("1. View Profile");
            System.out.println("2. View Courses");
            System.out.println("3. View My Marks");
            System.out.println("4. View My Result");
            System.out.println("5. Export My Result");
            System.out.println("6. Logout");
            System.out.print("Enter your choice: ");

            choice = readInt();

            switch (choice) {

                case 1:
                    viewProfile(student);
                    break;

                case 2:
                    viewCourses();
                    break;

                case 3:
                    viewMarks(studentId);
                    break;

                case 4:
                    viewResult(studentId);
                    break;

                case 5:
                    exportResult(studentId);
                    break;

                case 6:
                    System.out.println("Logging out...");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 6);
    }

    private void viewProfile(Student student) {

        System.out.println();
        System.out.println("=================================");
        System.out.println("          STUDENT PROFILE        ");
        System.out.println("=================================");

        student.displayBasicInfo();
        System.out.println("Department: " + student.getDepartment());
    }

    private void viewCourses() {

        System.out.println();
        System.out.println("--- Available Courses ---");

        List<Course> courses = courseDAO.getAllCourses();

        if (courses.isEmpty()) {
            System.out.println("No courses available.");
            return;
        }

        for (Course course : courses) {
            System.out.println("---------------------------------");
            course.displayCourse();
        }
    }

    private void viewMarks(int studentId) {

        System.out.println();
        System.out.println("--- My Marks ---");

        List<Marks> marksList = marksDAO.getMarksByStudentId(studentId);

        if (marksList.isEmpty()) {
            System.out.println("No marks available.");
            return;
        }

        for (Marks marks : marksList) {
            System.out.println("---------------------------------");
            marks.displayMarks();
        }
    }

    private void viewResult(int studentId) {

        System.out.println();
        System.out.println("=================================");
        System.out.println("           MY RESULT             ");
        System.out.println("=================================");

        List<Marks> marksList = marksDAO.getMarksByStudentId(studentId);

        if (marksList.isEmpty()) {
            System.out.println("No marks available.");
            return;
        }

        int totalMarks = 0;

        for (Marks marks : marksList) {

            totalMarks += marks.getMarks();

            Course course = courseDAO.getCourseById(marks.getCourseId());

            System.out.println("---------------------------------");

            if (course != null) {
                System.out.println("Course : " + course.getCourseCode()
                        + " - " + course.getCourseName());
            }

            System.out.println("Marks  : " + marks.getMarks());
            System.out.println("Grade  : " + marks.getGrade());
        }

        double percentage =
                (double) totalMarks / (marksList.size() * 100) * 100;

        System.out.println("---------------------------------");
        System.out.println("Total Marks : " + totalMarks);
        System.out.printf("Percentage  : %.2f%%\n", percentage);
        System.out.println("---------------------------------");
    }

    private void exportResult(int studentId) {

        reportService.exportStudentResult(studentId);

    }

    private int readInt() {

        while (true) {

            try {
                return Integer.parseInt(scanner.nextLine());

            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid number: ");
            }
        }
    }
}
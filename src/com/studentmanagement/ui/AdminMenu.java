//shri radha
package com.studentmanagement.ui;

import com.studentmanagement.dao.CourseDAO;
import com.studentmanagement.dao.MarksDAO;
import com.studentmanagement.model.Course;
import com.studentmanagement.model.Marks;
import com.studentmanagement.model.Student;
import com.studentmanagement.service.ReportService;
import com.studentmanagement.service.StudentService;
import com.studentmanagement.util.InputValidator;
import java.util.List;
import java.util.Scanner;

public class AdminMenu {

    private final Scanner scanner;
    private final StudentService studentService;
    private final CourseDAO courseDAO;
    private final MarksDAO marksDAO;
    private final ReportService reportService;

    public AdminMenu(Scanner scanner) {
        this.scanner = scanner;
        studentService = new StudentService();
        courseDAO = new CourseDAO();
        marksDAO = new MarksDAO();
        reportService = new ReportService();
    }

    public void showMenu() {

        int choice;

        do {
            System.out.println();
            System.out.println("=================================");
            System.out.println("          ADMIN / FACULTY        ");
            System.out.println("=================================");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Add Course");
            System.out.println("6. View Courses");
            System.out.println("7. Update Course");
            System.out.println("8. Delete Course");
            System.out.println("9. Enter Marks");
            System.out.println("10. View Marks");
            System.out.println("11. Update Marks");
            System.out.println("12. Delete Marks");
            System.out.println("13. Generate Report");
            System.out.println("14. Export Report");
            System.out.println("15. Logout");
            System.out.print("Enter your choice: ");

            choice = readInt();

            switch (choice) {

                case 1:
                    addStudent();
                    break;

                case 2:
                    viewStudents();
                    break;

                case 3:
                    updateStudent();
                    break;

                case 4:
                    deleteStudent();
                    break;

                case 5:
                    addCourse();
                    break;

                case 6:
                    viewCourses();
                    break;

                case 7:
                    updateCourse();
                    break;

                case 8:
                    deleteCourse();
                    break;

                case 9:
                    addMarks();
                    break;

                case 10:
                    viewMarks();
                    break;

                case 11:
                    updateMarks();
                    break;

                case 12:
                    deleteMarks();
                    break;

                case 13:
                    reportService.generateReport();
                    break;

                case 14:
                    reportService.exportReport();
                    break;

                case 15:
                    System.out.println("Logging out...");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 15);
    }

    private void addStudent() {

        System.out.println("\n--- Add Student ---");

        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        if (!InputValidator.isValidName(name)) {
            System.out.println("Invalid name.");
            return;
        }

        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        if (!InputValidator.isValidEmail(email)) {
            System.out.println("Invalid email.");
            return;
        }

        System.out.print("Enter department: ");
        String department = scanner.nextLine();

        Student student = new Student(
                0,
                name,
                email,
                department
        );

        if (studentService.addStudent(student)) {
            System.out.println("Student added successfully!");
        }
    }

    private void viewStudents() {

        System.out.println("\n--- Student List ---");

        List<Student> students = studentService.getAllStudents();

        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        for (Student student : students) {
            System.out.println("---------------------------------");
            student.displayBasicInfo();
            System.out.println("Department: " + student.getDepartment());
        }
    }

    private void updateStudent() {

        System.out.print("Enter student ID: ");
        int id = readInt();

        Student student = studentService.getStudentById(id);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.print("Enter new name: ");
        String name = scanner.nextLine();

        System.out.print("Enter new email: ");
        String email = scanner.nextLine();

        System.out.print("Enter new department: ");
        String department = scanner.nextLine();

        student.setName(name);
        student.setEmail(email);
        student.setDepartment(department);

        if (studentService.updateStudent(student)) {
            System.out.println("Student updated successfully!");
        }
    }

    private void deleteStudent() {

        System.out.print("Enter student ID: ");
        int id = readInt();

        if (studentService.deleteStudent(id)) {
            System.out.println("Student deleted successfully!");
        } else {
            System.out.println("Student not found.");
        }
    }

    private void addCourse() {

        System.out.println("\n--- Add Course ---");

        System.out.print("Enter course code: ");
        String code = scanner.nextLine();

        System.out.print("Enter course name: ");
        String name = scanner.nextLine();

        System.out.print("Enter credits: ");
        int credits = readInt();

        if (!InputValidator.isValidCredits(credits)) {
            System.out.println("Credits must be greater than 0.");
            return;
        }

        Course course = new Course(
                0,
                code,
                name,
                credits
        );

        if (courseDAO.addCourse(course)) {
            System.out.println("Course added successfully!");
        }
    }

    private void viewCourses() {

        System.out.println("\n--- Course List ---");

        List<Course> courses = courseDAO.getAllCourses();

        if (courses.isEmpty()) {
            System.out.println("No courses found.");
            return;
        }

        for (Course course : courses) {
            System.out.println("---------------------------------");
            course.displayCourse();
        }
    }

    private void updateCourse() {

        System.out.print("Enter course ID: ");
        int id = readInt();

        Course course = courseDAO.getCourseById(id);

        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        System.out.print("Enter new course code: ");
        String code = scanner.nextLine();

        System.out.print("Enter new course name: ");
        String name = scanner.nextLine();

        System.out.print("Enter new credits: ");
        int credits = readInt();

        course.setCourseCode(code);
        course.setCourseName(name);
        course.setCredits(credits);

        if (courseDAO.updateCourse(course)) {
            System.out.println("Course updated successfully!");
        }
    }

    private void deleteCourse() {

        System.out.print("Enter course ID: ");
        int id = readInt();

        if (courseDAO.deleteCourse(id)) {
            System.out.println("Course deleted successfully!");
        } else {
            System.out.println("Course not found.");
        }
    }

    private void addMarks() {

        System.out.println("\n--- Enter Marks ---");

        // Show available students
        System.out.println("\n--- Available Students ---");

        List<Student> students = studentService.getAllStudents();

        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        for (Student student : students) {
            System.out.println("---------------------------------");
            System.out.println("Student ID   : " + student.getId());
            System.out.println("Name         : " + student.getName());
            System.out.println("Department   : " + student.getDepartment());
        }

        System.out.println("---------------------------------");

        System.out.print("Enter student ID: ");
        int studentId = readInt();

        if (studentService.getStudentById(studentId) == null) {
            System.out.println("Student not found.");
            return;
        }

        // Show available courses
        System.out.println("\n--- Available Courses ---");

        List<Course> courses = courseDAO.getAllCourses();

        if (courses.isEmpty()) {
            System.out.println("No courses found.");
            return;
        }

        for (Course course : courses) {
            System.out.println("---------------------------------");
            System.out.println("Course ID   : " + course.getCourseId());
            System.out.println("Course Code : " + course.getCourseCode());
            System.out.println("Course Name : " + course.getCourseName());
            System.out.println("Credits     : " + course.getCredits());
        }

        System.out.println("---------------------------------");

        System.out.print("Enter course ID: ");
        int courseId = readInt();

        Course selectedCourse = courseDAO.getCourseById(courseId);

        if (selectedCourse == null) {
            System.out.println("Course not found.");
            return;
        }

        System.out.println(
                "Selected Course: "
                        + selectedCourse.getCourseCode()
                        + " - "
                        + selectedCourse.getCourseName()
        );

        System.out.print("Enter marks (0-100): ");
        int marksValue = readInt();

        if (!InputValidator.isValidMarks(marksValue)) {
            System.out.println("Marks must be between 0 and 100.");
            return;
        }

        Marks marks = new Marks(
                0,
                studentId,
                courseId,
                marksValue
        );

        if (marksDAO.addMarks(marks)) {
            System.out.println("Marks added successfully!");
            System.out.println("Grade: " + marks.getGrade());
        }
    }

    private void viewMarks() {

        System.out.println("\n--- Marks List ---");

        List<Marks> marksList = marksDAO.getAllMarks();

        if (marksList.isEmpty()) {
            System.out.println("No marks found.");
            return;
        }

        for (Marks marks : marksList) {
            System.out.println("---------------------------------");
            marks.displayMarks();
        }
    }

    private void updateMarks() {

        System.out.print("Enter mark ID: ");
        int id = readInt();

        System.out.print("Enter new marks (0-100): ");
        int marksValue = readInt();

        if (!InputValidator.isValidMarks(marksValue)) {
            System.out.println("Marks must be between 0 and 100.");
            return;
        }

        Marks marks = new Marks(
                id,
                0,
                0,
                marksValue
        );

        if (marksDAO.updateMarks(marks)) {
            System.out.println("Marks updated successfully!");
            System.out.println("New Grade: " + marks.getGrade());
        } else {
            System.out.println("Mark record not found.");
        }
    }

    private void deleteMarks() {

        System.out.print("Enter mark ID: ");
        int id = readInt();

        if (marksDAO.deleteMarks(id)) {
            System.out.println("Marks deleted successfully!");
        } else {
            System.out.println("Mark record not found.");
        }
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
//SHRI RADHA
package com.studentmanagement.service;

import com.studentmanagement.dao.StudentDAO;
import com.studentmanagement.exception.InvalidMarksException;
import com.studentmanagement.model.Student;

import java.util.List;

public class StudentService {

    private final StudentDAO studentDAO;

    public StudentService() {
        studentDAO = new StudentDAO();
    }

    // Add student
    public boolean addStudent(Student student) {
        return studentDAO.addStudent(student);
    }

    // Get all students
    public List<Student> getAllStudents() {
        return studentDAO.getAllStudents();
    }

    // Get student by ID
    public Student getStudentById(int studentId) {
        return studentDAO.getStudentById(studentId);
    }

    // Update student
    public boolean updateStudent(Student student) {
        return studentDAO.updateStudent(student);
    }

    // Delete student
    public boolean deleteStudent(int studentId) {
        return studentDAO.deleteStudent(studentId);
    }

    // Validate marks
    public void validateMarks(int marks) throws InvalidMarksException {

        if (marks < 0 || marks > 100) {
            throw new InvalidMarksException(
                    "Marks must be between 0 and 100."
            );
        }
    }
}
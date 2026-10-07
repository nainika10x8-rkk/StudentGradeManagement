//SHRI RADHA
package com.studentmanagement.dao;

import com.studentmanagement.model.Marks;
import com.studentmanagement.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MarksDAO {

    // Add marks
    public boolean addMarks(Marks marks) {

        String sql = "INSERT INTO marks (student_id, course_id, marks, grade) VALUES (?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, marks.getStudentId());
            statement.setInt(2, marks.getCourseId());
            statement.setInt(3, marks.getMarks());
            statement.setString(4, marks.getGrade());

            int rows = statement.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            System.out.println("Error adding marks: " + e.getMessage());
            return false;
        }
    }

    // Get all marks
    public List<Marks> getAllMarks() {

        List<Marks> marksList = new ArrayList<>();

        String sql = "SELECT * FROM marks";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                Marks marks = new Marks(
                        resultSet.getInt("mark_id"),
                        resultSet.getInt("student_id"),
                        resultSet.getInt("course_id"),
                        resultSet.getInt("marks")
                );

                marksList.add(marks);
            }

        } catch (SQLException e) {
            System.out.println("Error fetching marks: " + e.getMessage());
        }

        return marksList;
    }

    // Get marks for a specific student
    public List<Marks> getMarksByStudentId(int studentId) {

        List<Marks> marksList = new ArrayList<>();

        String sql = "SELECT * FROM marks WHERE student_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, studentId);

            try (ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {

                    Marks marks = new Marks(
                            resultSet.getInt("mark_id"),
                            resultSet.getInt("student_id"),
                            resultSet.getInt("course_id"),
                            resultSet.getInt("marks")
                    );

                    marksList.add(marks);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error fetching student marks: " + e.getMessage());
        }

        return marksList;
    }

    // Update marks
    public boolean updateMarks(Marks marks) {

        String sql = "UPDATE marks SET marks = ?, grade = ? WHERE mark_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, marks.getMarks());
            statement.setString(2, marks.getGrade());
            statement.setInt(3, marks.getMarkId());

            int rows = statement.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            System.out.println("Error updating marks: " + e.getMessage());
            return false;
        }
    }

    // Delete marks
    public boolean deleteMarks(int markId) {

        String sql = "DELETE FROM marks WHERE mark_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, markId);

            int rows = statement.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            System.out.println("Error deleting marks: " + e.getMessage());
            return false;
        }
    }
}
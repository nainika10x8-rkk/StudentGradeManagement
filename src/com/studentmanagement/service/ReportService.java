//shri radha
package com.studentmanagement.service;

import com.studentmanagement.dao.MarksDAO;
import com.studentmanagement.model.Marks;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ReportService implements ReportGenerator {

    private final MarksDAO marksDAO;

    public ReportService() {
        marksDAO = new MarksDAO();
    }

    @Override
    public void generateReport() {

        List<Marks> marksList = marksDAO.getAllMarks();

        System.out.println();
        System.out.println("=================================");
        System.out.println("        STUDENT MARKS REPORT     ");
        System.out.println("=================================");

        if (marksList.isEmpty()) {
            System.out.println("No marks available.");
            return;
        }

        for (Marks marks : marksList) {
            System.out.println("---------------------------------");
            marks.displayMarks();
        }

        System.out.println("---------------------------------");
        System.out.println("Total Records: " + marksList.size());
    }

    public void exportReport() {

        List<Marks> marksList = marksDAO.getAllMarks();

        File directory = new File("reports");

        if (!directory.exists()) {
            directory.mkdirs();
        }

        File file = new File(directory, "student_marks_report.txt");

        try (FileWriter writer = new FileWriter(file)) {

            writer.write("=================================\n");
            writer.write("        STUDENT MARKS REPORT\n");
            writer.write("=================================\n\n");

            for (Marks marks : marksList) {

                writer.write("---------------------------------\n");
                writer.write("Mark ID    : " + marks.getMarkId() + "\n");
                writer.write("Student ID : " + marks.getStudentId() + "\n");
                writer.write("Course ID  : " + marks.getCourseId() + "\n");
                writer.write("Marks      : " + marks.getMarks() + "\n");
                writer.write("Grade      : " + marks.getGrade() + "\n");
            }

            writer.write("---------------------------------\n");
            writer.write("Total Records: " + marksList.size() + "\n");

            System.out.println("Report exported successfully!");
            System.out.println("Location: " + file.getPath());

        } catch (IOException e) {
            System.out.println("Error exporting report: " + e.getMessage());
        }
    }

    // Export result for a specific student
    public void exportStudentResult(int studentId) {

        List<Marks> marksList = marksDAO.getMarksByStudentId(studentId);

        File directory = new File("reports");

        if (!directory.exists()) {
            directory.mkdirs();
        }

        File file = new File(
                directory,
                "student_" + studentId + "_result.txt"
        );

        try (FileWriter writer = new FileWriter(file)) {

            writer.write("=================================\n");
            writer.write("          STUDENT RESULT\n");
            writer.write("=================================\n\n");

            if (marksList.isEmpty()) {

                writer.write("No result available.\n");
                System.out.println("No result available for this student.");
                return;
            }

            int totalMarks = 0;

            for (Marks marks : marksList) {

                writer.write("---------------------------------\n");
                writer.write("Course ID  : " + marks.getCourseId() + "\n");
                writer.write("Marks      : " + marks.getMarks() + "\n");
                writer.write("Grade      : " + marks.getGrade() + "\n");

                totalMarks += marks.getMarks();
            }

            double percentage =
                    (double) totalMarks / (marksList.size() * 100) * 100;

            writer.write("---------------------------------\n");
            writer.write("Total Marks : " + totalMarks + "\n");
            writer.write(String.format(
                    "Percentage  : %.2f%%\n",
                    percentage
            ));
            writer.write("---------------------------------\n");

            System.out.println("Student result exported successfully!");
            System.out.println("Location: " + file.getPath());

        } catch (IOException e) {
            System.out.println(
                    "Error exporting student result: "
                    + e.getMessage()
            );
        }
    }
}
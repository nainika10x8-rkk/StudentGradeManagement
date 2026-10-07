// SHRI RADHA 
package com.studentmanagement;


import com.studentmanagement.model.Student;
import com.studentmanagement.ui.AdminMenu;
import com.studentmanagement.ui.StudentMenu;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int choice;

        do {
            System.out.println();
            System.out.println("=================================");
            System.out.println("   STUDENT COURSE & GRADE        ");
            System.out.println("        MANAGEMENT SYSTEM        ");
            System.out.println("=================================");
            System.out.println("1. Admin / Faculty Login");
            System.out.println("2. Student Login");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {

                    case 1:
                        AdminMenu adminMenu = new AdminMenu(scanner);
                        adminMenu.showMenu();
                        break;

                    case 2:
                        studentLogin(scanner);
                        break;

                    case 3:
                        System.out.println("Thank you for using the system!");
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }

            } catch (NumberFormatException e) {

                System.out.println("Please enter a valid number.");
                choice = 0;
            }

        } while (choice != 3);

        scanner.close();
    }

    private static void studentLogin(Scanner scanner) {

        System.out.println();
        System.out.println("=================================");
        System.out.println("          STUDENT LOGIN          ");
        System.out.println("=================================");

        System.out.print("Enter Student ID: ");

        try {

            int studentId = Integer.parseInt(scanner.nextLine());

            Student student = new com.studentmanagement.service.StudentService()
                    .getStudentById(studentId);

            if (student == null) {
                System.out.println("Student not found.");
                return;
            }

            System.out.println("Login successful!");

            StudentMenu studentMenu = new StudentMenu(scanner);
            studentMenu.showMenu(studentId);

        } catch (NumberFormatException e) {

            System.out.println("Invalid Student ID.");

        }
    }
}
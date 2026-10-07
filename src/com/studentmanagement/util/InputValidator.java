//  shri radha
package com.studentmanagement.util;

public class InputValidator {

    public static boolean isValidName(String name) {
        return name != null && !name.trim().isEmpty();
    }

    public static boolean isValidEmail(String email) {
        return email != null && email.matches(
                "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"
        );
    }

    public static boolean isValidMarks(int marks) {
        return marks >= 0 && marks <= 100;
    }

    public static boolean isValidCredits(int credits) {
        return credits > 0;
    }
}
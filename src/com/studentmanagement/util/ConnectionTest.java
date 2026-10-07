//shri radha
package com.studentmanagement.util;

import java.sql.Connection;

public class ConnectionTest {

    public static void main(String[] args) {

        try {
            Connection connection = DBConnection.getConnection();

            System.out.println("=================================");
            System.out.println(" DATABASE CONNECTION SUCCESSFUL ");
            System.out.println("=================================");

            connection.close();

        } catch (Exception e) {
            System.out.println("Database connection failed!");
            System.out.println("Error: " + e.getMessage());
        }
    }
    }
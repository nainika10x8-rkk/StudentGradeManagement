//shri radha
package com.studentmanagement.model;

public class Faculty extends User {

    private String department;

    public Faculty(int id, String name, String email, String department) {
        super(id, name, email);
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public void displayDashboard() {
        System.out.println("=================================");
        System.out.println("         FACULTY DASHBOARD       ");
        System.out.println("=================================");
        displayBasicInfo();
        System.out.println("Department: " + department);
    }
}

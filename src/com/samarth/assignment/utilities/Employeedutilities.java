package com.samarth.assignment.utilities;

import com.samarth.assignment.employees.Employee;

// Utility class for employee operations.
 
public class Employeedutilities {

    // Display Employee Information
    public void displayEmployee(Employee employee) {

        System.out.println("--------------------------------");

        System.out.println("Employee Name : "
                + employee.getName());

        System.out.println("Employee ID   : "
                + employee.getEmployeeId());

        System.out.println("Salary        : "
                + employee.getSalary());

        System.out.println("--------------------------------");
    }

    // Increase Salary
    public void increaseSalary(Employee employee,
                               double amount) {

        employee.setSalary(employee.getSalary() + amount);

        System.out.println("Salary Updated Successfully.");
    }

}
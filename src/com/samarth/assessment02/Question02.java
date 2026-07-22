package com.samarth.assessment02;

import java.util.Scanner;

// student class
class Student {

    // default constructor
    Student() {
        System.out.println("Student object is created");
    }
}
// commission class
class Commission {

    // data members
    String name;
    String address;
    String phone;
    double salesAmount;

    Scanner sc = new Scanner(System.in);

    // methods
    public void acceptDetails() {

        name = sc.nextLine();
        address = sc.nextLine();
        phone = sc.nextLine();
        salesAmount = sc.nextDouble();
    }

    // method to calculate commission
    public void calculateCommission() {
        double commission = 0;

        if (salesAmount >= 100000) {
            commission = salesAmount * 0.10;
        } else if (salesAmount >= 50000) {
            commission = salesAmount * 0.05;
        } else if (salesAmount >= 30000) {
            commission = salesAmount * 0.03;
        } else {
            commission = 0;
        }

        System.out.println("Name         : " + name);
        System.out.println("Address      : " + address);
        System.out.println("Phone        : " + phone);
        System.out.println("Sales Amount : " + salesAmount);
        System.out.println("Commission   : " + commission);
    }
}

// main class
public class Question02 {

    public static void main(String[] args) {

        Student s = new Student();
        Commission c = new Commission();
        c.acceptDetails();
        c.calculateCommission();
    }
}
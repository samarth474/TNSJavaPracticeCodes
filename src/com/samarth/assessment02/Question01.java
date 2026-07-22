package com.samarth.assessment02;
import java.util.Scanner;

public class Question01 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        int roll = sc.nextInt();
        char grade = sc.next().charAt(0);
        double percentage = sc.nextDouble();

        System.out.println("Name       : " + name);
        System.out.println("Roll No    : " + roll);
        System.out.println("Grade      : " + grade);
        System.out.println("Percentage : " + percentage + "%");

        sc.close();
    }
}
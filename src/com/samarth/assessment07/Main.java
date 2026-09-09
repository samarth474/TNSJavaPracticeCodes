package com.samarth.assessment07;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int choice = sc.nextInt();
        int hours = sc.nextInt();
        double costPerHour = sc.nextDouble();

        Airfare airfare;

        switch (choice) {

        case 1:
            airfare = new AirIndia(hours, costPerHour);
            break;

        case 2:
            airfare = new KingFisher(hours, costPerHour);
            break;

        case 3:
            airfare = new Indigo(hours, costPerHour);
            break;

        default:
            System.out.println("Invalid choice");
            sc.close();
            return;
        }

        double amount = airfare.calculateAmount();

        System.out.printf("%.2f%n", amount);

        sc.close();
    }
}
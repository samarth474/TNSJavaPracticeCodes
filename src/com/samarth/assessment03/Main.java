package com.samarth.assessment03;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Booking details
        String details = sc.nextLine();

        String[] data = details.split(",");

        String stageEvent = data[0];
        String customer = data[1];
        Integer noOfSeats = Integer.parseInt(data[2]);

        ticketbooking booking =
                new ticketbooking(stageEvent, customer, noOfSeats);

        // Payment choice
        int choice = sc.nextInt();

        if (choice == 1) {

            double amount = sc.nextDouble();

            booking.makePayment(amount);

        } else if (choice == 2) {

            double amount = sc.nextDouble();
            sc.nextLine();

            String walletNumber = sc.nextLine();

            booking.makePayment(amount, walletNumber);

        } else if (choice == 3) {

            sc.nextLine();

            String name = sc.nextLine();

            double amount = sc.nextDouble();
            sc.nextLine();

            String cardType = sc.nextLine();

            String ccv = sc.nextLine();

            booking.makePayment(amount, cardType, name, ccv);

        } else {

            System.out.println("Invalid choice");
        }

        sc.close();
    }
}
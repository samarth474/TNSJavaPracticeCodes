package com.samarth.assessment06;

public class BankSystem {

    public static void main(String[] args) {

        // Create a shared bank account
        BankAccount account = new BankAccount(1001, 10000);

        // Thread 1 - deposits money
        Thread customer1 = new Thread(() -> {

            account.deposit(2000);
            account.withdraw(1000);

        }, "Customer-1");

        // Thread 2 - deposits and withdraws money
        Thread customer2 = new Thread(() -> {

            account.deposit(3000);
            account.withdraw(2500);

        }, "Customer-2");

        // Thread 3 - performs another transaction
        Thread customer3 = new Thread(() -> {

            account.withdraw(4000);
            account.deposit(1500);

        }, "Customer-3");

        System.out.println("Starting banking transactions...\n");

        // Start all threads
        customer1.start();
        customer2.start();
        customer3.start();

        // Wait for all threads to complete
        try {

            customer1.join();
            customer2.join();
            customer3.join();

        } catch (InterruptedException e) {

            System.out.println("Thread interrupted: " + e.getMessage());
        }

        System.out.println("\nAll transactions completed.\n");

        // Display final balance
        account.displayBalance();
    }
}
package com.samarth.assessment06;

public class BankAccount {

    private int accountNumber;
    private double balance;

    public BankAccount(int accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    // Synchronized method prevents multiple threads
    // from modifying the balance at the same time.
    public synchronized void deposit(double amount) {

        System.out.println(
                Thread.currentThread().getName()
                + " depositing " + amount
        );

        balance = balance + amount;

        System.out.println(
                Thread.currentThread().getName()
                + " completed deposit."
        );
    }

    // Synchronized method ensures safe withdrawal
    // when multiple threads access the same account.
    public synchronized void withdraw(double amount) {

        System.out.println(
                Thread.currentThread().getName()
                + " withdrawing " + amount
        );

        if (amount <= balance) {

            balance = balance - amount;

            System.out.println(
                    Thread.currentThread().getName()
                    + " completed withdrawal."
            );

        } else {

            System.out.println(
                    Thread.currentThread().getName()
                    + " - Insufficient balance."
            );
        }
    }

    public synchronized void displayBalance() {

        System.out.println(
                "Account Number: " + accountNumber
        );

        System.out.println(
                "Current Balance: " + balance
        );
    }
}
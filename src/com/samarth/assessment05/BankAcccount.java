package com.samarth.assessment05;

// Custom exception for insufficient balance
class InsufficientFundsException extends Exception {

    public InsufficientFundsException(String message) {
        super(message);
    }
}

// Custom exception for invalid arguments
class InvalidArgumentException extends Exception {

    public InvalidArgumentException(String message) {
        super(message);
    }
}

// BankAccount class
public class BankAcccount {

    private int accountNumber;
    private double balance;

    // Parameterized constructor
    public BankAcccount(int accountNumber, double balance)
            throws InvalidArgumentException {

        if (accountNumber <= 0) {
            throw new InvalidArgumentException(
                    "Account number must be positive."
            );
        }

        if (balance < 0) {
            throw new InvalidArgumentException(
                    "Balance cannot be negative."
            );
        }

        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    // Deposit money into the account
    public void deposit(double amount)
            throws InvalidArgumentException {

        if (amount <= 0) {
            throw new InvalidArgumentException(
                    "Deposit amount must be greater than zero."
            );
        }

        balance = balance + amount;

        System.out.println("Deposited: " + amount);
    }

    // Withdraw money from the account
    public void withdraw(double amount)
            throws InvalidArgumentException, InsufficientFundsException {

        if (amount <= 0) {
            throw new InvalidArgumentException(
                    "Withdrawal amount must be greater than zero."
            );
        }

        if (amount > balance) {
            throw new InsufficientFundsException(
                    "Insufficient funds."
            );
        }

        balance = balance - amount;

        System.out.println("Withdrawn: " + amount);
    }

    // Display account number and balance
    public void displayBalance() {

        System.out.println("Account Number: " + accountNumber);
        System.out.println("Current Balance: " + balance);
    }
}
package com.samarth.assessment05;

public class BankingSystem {

    public static void main(String[] args) {

        try {

            // Create BankAccount object
            BankAcccount account = new BankAcccount(1001, 5000.0);

            // Display initial balance
            account.displayBalance();

            System.out.println();

            // Deposit operation
            account.deposit(2000);

            // Withdrawal operation
            account.withdraw(1000);

            System.out.println();

            // Display final balance
            account.displayBalance();

        }
        catch (InsufficientFundsException e) {

            System.out.println("Error: " + e.getMessage());

        }
        catch (InvalidArgumentException e) {

            System.out.println("Error: " + e.getMessage());

        }
        finally {

            System.out.println();
            System.out.println("Banking operation completed.");

        }
    }
}
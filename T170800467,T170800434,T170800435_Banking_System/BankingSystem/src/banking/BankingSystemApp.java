package banking;

import banking.model.Account;
import banking.model.Beneficiary;
import banking.model.Customer;
import banking.model.Transaction;
import banking.service.BankingService;
import banking.service.BankingServiceImpl;

import java.util.List;
import java.util.Scanner;

/**
 * Main application class providing a console menu interface for the Banking System.
 */
public class BankingSystemApp {

    private static int transactionIdCounter = 1;

    public static void main(String[] args) {
        // Polymorphic declaration using BankingService reference pointing to BankingServiceImpl
        BankingService bankingService = new BankingServiceImpl();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            displayMenu();
            int choice = readInt(scanner, "Enter your choice: ");

            switch (choice) {
                case 1:
                    handleAddCustomer(bankingService, scanner);
                    break;
                case 2:
                    handleAddAccount(bankingService, scanner);
                    break;
                case 3:
                    handleAddBeneficiary(bankingService, scanner);
                    break;
                case 4:
                    handleAddTransaction(bankingService, scanner);
                    break;
                case 5:
                    handleFindCustomer(bankingService, scanner);
                    break;
                case 6:
                    handleListAccounts(bankingService, scanner);
                    break;
                case 7:
                    handleListTransactions(bankingService, scanner);
                    break;
                case 8:
                    handleListBeneficiaries(bankingService, scanner);
                    break;
                case 9:
                    System.out.println("Thank you for using Banking System!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please select an option between 1 and 9.");
            }
            System.out.println();
        }

        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("================================");
        System.out.println("        BANKING SYSTEM          ");
        System.out.println("================================");
        System.out.println("1. Add Customer");
        System.out.println("2. Add Account");
        System.out.println("3. Add Beneficiary");
        System.out.println("4. Add Transaction");
        System.out.println("5. Find Customer by ID");
        System.out.println("6. List All Accounts of Customer");
        System.out.println("7. List All Transactions of Account");
        System.out.println("8. List All Beneficiaries of Customer");
        System.out.println("9. Exit");
        System.out.println("================================");
    }

    private static void handleAddCustomer(BankingService service, Scanner scanner) {
        System.out.println("\n--- Add Customer ---");
        int customerId = readInt(scanner, "Enter Customer ID: ");
        if (customerId <= 0) {
            System.out.println("Error: Customer ID must be a positive integer.");
            return;
        }

        String name = readString(scanner, "Enter Name: ");
        String address = readString(scanner, "Enter Address: ");
        String contact = readString(scanner, "Enter Contact: ");

        Customer customer = new Customer(customerId, name, address, contact);
        service.addCustomer(customer);
    }

    private static void handleAddAccount(BankingService service, Scanner scanner) {
        System.out.println("\n--- Add Account ---");
        int accountId = readInt(scanner, "Enter Account ID: ");
        if (accountId <= 0) {
            System.out.println("Error: Account ID must be a positive integer.");
            return;
        }

        int customerId = readInt(scanner, "Enter Customer ID: ");
        if (customerId <= 0) {
            System.out.println("Error: Customer ID must be a positive integer.");
            return;
        }

        String accountType = readString(scanner, "Enter Account Type (Saving/Current): ");
        if (!accountType.equalsIgnoreCase("Saving") && !accountType.equalsIgnoreCase("Current")) {
            System.out.println("Error: Account type must be 'Saving' or 'Current'.");
            return;
        }

        double initialBalance = readDouble(scanner, "Enter Initial Balance: ");
        if (initialBalance < 0) {
            System.out.println("Error: Initial account balance cannot be negative.");
            return;
        }

        // Format to exact casing expected ("Saving" / "Current")
        String formattedType = accountType.equalsIgnoreCase("Saving") ? "Saving" : "Current";

        Account account = new Account(accountId, customerId, formattedType, initialBalance);
        service.addAccount(account);
    }

    private static void handleAddBeneficiary(BankingService service, Scanner scanner) {
        System.out.println("\n--- Add Beneficiary ---");
        int customerId = readInt(scanner, "Enter Customer ID: ");
        if (customerId <= 0) {
            System.out.println("Error: Customer ID must be a positive integer.");
            return;
        }

        int beneficiaryId = readInt(scanner, "Enter Beneficiary ID: ");
        if (beneficiaryId <= 0) {
            System.out.println("Error: Beneficiary ID must be a positive integer.");
            return;
        }

        String name = readString(scanner, "Enter Beneficiary Name: ");
        String accountNumber = readString(scanner, "Enter Beneficiary Account Number: ");
        String bankDetails = readString(scanner, "Enter Bank Details: ");

        Beneficiary beneficiary = new Beneficiary(beneficiaryId, customerId, name, accountNumber, bankDetails);
        service.addBeneficiary(beneficiary);
    }

    private static void handleAddTransaction(BankingService service, Scanner scanner) {
        System.out.println("\n--- Add Transaction ---");
        int accountId = readInt(scanner, "Enter Account ID: ");
        if (accountId <= 0) {
            System.out.println("Error: Account ID must be a positive integer.");
            return;
        }

        Account account = service.findAccountById(accountId);
        if (account == null) {
            System.out.println("Error: Account ID " + accountId + " does not exist.");
            return;
        }

        String typeInput = readString(scanner, "Enter Transaction Type (Deposit/Withdrawal): ");
        if (!typeInput.equalsIgnoreCase("Deposit") && !typeInput.equalsIgnoreCase("Withdrawal")) {
            System.out.println("Error: Transaction type must be 'Deposit' or 'Withdrawal'.");
            return;
        }

        double amount = readDouble(scanner, "Enter Amount: ");
        if (amount <= 0) {
            System.out.println("Error: Transaction amount must be greater than zero.");
            return;
        }

        String formattedType = typeInput.equalsIgnoreCase("Deposit") ? "Deposit" : "Withdrawal";
        int transactionId = transactionIdCounter++;

        Transaction transaction = new Transaction(transactionId, accountId, formattedType, amount);
        boolean success = service.addTransaction(transaction);

        if (success) {
            System.out.println("Transaction ID: " + transactionId);
            System.out.println("Updated Account Balance: $" + String.format("%.2f", account.getBalance()));
        } else {
            // Revert ID increment if transaction failed
            transactionIdCounter--;
        }
    }

    private static void handleFindCustomer(BankingService service, Scanner scanner) {
        System.out.println("\n--- Find Customer by ID ---");
        int customerId = readInt(scanner, "Enter Customer ID: ");
        Customer customer = service.findCustomerById(customerId);

        if (customer != null) {
            System.out.println("Customer Details:");
            System.out.println(customer);
        } else {
            System.out.println("Customer not found.");
        }
    }

    private static void handleListAccounts(BankingService service, Scanner scanner) {
        System.out.println("\n--- List Accounts of Customer ---");
        int customerId = readInt(scanner, "Enter Customer ID: ");
        List<Account> accounts = service.getAccountsByCustomerId(customerId);

        if (accounts.isEmpty()) {
            System.out.println("No accounts found for Customer ID " + customerId + ".");
        } else {
            System.out.println("Accounts for Customer ID " + customerId + ":");
            for (Account acc : accounts) {
                System.out.println(acc);
            }
        }
    }

    private static void handleListTransactions(BankingService service, Scanner scanner) {
        System.out.println("\n--- List Transactions of Account ---");
        int accountId = readInt(scanner, "Enter Account ID: ");
        List<Transaction> transactions = service.getTransactionsByAccountId(accountId);

        if (transactions.isEmpty()) {
            System.out.println("No transactions found for Account ID " + accountId + ".");
        } else {
            System.out.println("Transactions for Account ID " + accountId + ":");
            for (Transaction tx : transactions) {
                System.out.println(tx);
            }
        }
    }

    private static void handleListBeneficiaries(BankingService service, Scanner scanner) {
        System.out.println("\n--- List Beneficiaries of Customer ---");
        int customerId = readInt(scanner, "Enter Customer ID: ");
        List<Beneficiary> beneficiaries = service.getBeneficiariesByCustomerId(customerId);

        if (beneficiaries.isEmpty()) {
            System.out.println("No beneficiaries found for Customer ID " + customerId + ".");
        } else {
            System.out.println("Beneficiaries for Customer ID " + customerId + ":");
            for (Beneficiary b : beneficiaries) {
                System.out.println(b);
            }
        }
    }

    // Input Helper Methods for Exception-Safe Console Input

    private static int readInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }
    }

    private static double readDouble(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid numeric amount.");
            }
        }
    }

    private static String readString(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("Input cannot be empty. Please try again.");
        }
    }
}

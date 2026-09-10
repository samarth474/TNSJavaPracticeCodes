package banking.service;

import banking.model.Account;
import banking.model.Beneficiary;
import banking.model.Customer;
import banking.model.Transaction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementation of BankingService managing records using Java Collections (HashMap).
 */
public class BankingServiceImpl implements BankingService {

    private Map<Integer, Customer> customers = new HashMap<>();
    private Map<Integer, Account> accounts = new HashMap<>();
    private Map<Integer, Transaction> transactions = new HashMap<>();
    private Map<Integer, Beneficiary> beneficiaries = new HashMap<>();

    @Override
    public boolean addCustomer(Customer customer) {
        if (customer == null) {
            System.out.println("Error: Customer object cannot be null.");
            return false;
        }
        if (customer.getCustomerID() <= 0) {
            System.out.println("Error: Customer ID must be a positive integer.");
            return false;
        }
        if (customers.containsKey(customer.getCustomerID())) {
            System.out.println("Error: Customer ID " + customer.getCustomerID() + " already exists.");
            return false;
        }
        customers.put(customer.getCustomerID(), customer);
        System.out.println("Customer added successfully.");
        return true;
    }

    @Override
    public boolean addAccount(Account account) {
        if (account == null) {
            System.out.println("Error: Account object cannot be null.");
            return false;
        }
        if (account.getAccountID() <= 0) {
            System.out.println("Error: Account ID must be a positive integer.");
            return false;
        }
        if (accounts.containsKey(account.getAccountID())) {
            System.out.println("Error: Account ID " + account.getAccountID() + " already exists.");
            return false;
        }
        if (!customers.containsKey(account.getCustomerID())) {
            System.out.println("Error: Customer ID " + account.getCustomerID() + " does not exist. Account creation failed.");
            return false;
        }
        if (account.getBalance() < 0) {
            System.out.println("Error: Initial account balance cannot be negative.");
            return false;
        }
        String type = account.getType();
        if (type == null || (!type.equalsIgnoreCase("Saving") && !type.equalsIgnoreCase("Current"))) {
            System.out.println("Error: Account type must be 'Saving' or 'Current'.");
            return false;
        }

        accounts.put(account.getAccountID(), account);
        System.out.println("Account added successfully.");
        return true;
    }

    @Override
    public boolean addBeneficiary(Beneficiary beneficiary) {
        if (beneficiary == null) {
            System.out.println("Error: Beneficiary object cannot be null.");
            return false;
        }
        if (beneficiary.getBeneficiaryID() <= 0) {
            System.out.println("Error: Beneficiary ID must be a positive integer.");
            return false;
        }
        if (beneficiaries.containsKey(beneficiary.getBeneficiaryID())) {
            System.out.println("Error: Beneficiary ID " + beneficiary.getBeneficiaryID() + " already exists.");
            return false;
        }
        if (!customers.containsKey(beneficiary.getCustomerID())) {
            System.out.println("Error: Customer ID " + beneficiary.getCustomerID() + " does not exist. Cannot add beneficiary.");
            return false;
        }
        beneficiaries.put(beneficiary.getBeneficiaryID(), beneficiary);
        System.out.println("Beneficiary added successfully.");
        return true;
    }

    @Override
    public boolean addTransaction(Transaction transaction) {
        if (transaction == null) {
            System.out.println("Error: Transaction object cannot be null.");
            return false;
        }
        if (transaction.getTransactionID() <= 0) {
            System.out.println("Error: Transaction ID must be a positive integer.");
            return false;
        }
        if (transactions.containsKey(transaction.getTransactionID())) {
            System.out.println("Error: Transaction ID " + transaction.getTransactionID() + " already exists.");
            return false;
        }

        Account account = accounts.get(transaction.getAccountID());
        if (account == null) {
            System.out.println("Error: Account ID " + transaction.getAccountID() + " does not exist. Cannot perform transaction.");
            return false;
        }

        if (transaction.getAmount() <= 0) {
            System.out.println("Error: Transaction amount must be greater than zero.");
            return false;
        }

        String type = transaction.getType();
        if (type == null) {
            System.out.println("Error: Transaction type cannot be null.");
            return false;
        }

        if (type.equalsIgnoreCase("Deposit")) {
            account.setBalance(account.getBalance() + transaction.getAmount());
            transactions.put(transaction.getTransactionID(), transaction);
            System.out.println("Deposit successful!");
            return true;
        } else if (type.equalsIgnoreCase("Withdrawal")) {
            if (transaction.getAmount() > account.getBalance()) {
                System.out.println("Error: Insufficient balance. Current Balance: $" +
                        String.format("%.2f", account.getBalance()) + ", Requested Withdrawal: $" +
                        String.format("%.2f", transaction.getAmount()));
                return false;
            }
            account.setBalance(account.getBalance() - transaction.getAmount());
            transactions.put(transaction.getTransactionID(), transaction);
            System.out.println("Withdrawal successful!");
            return true;
        } else {
            System.out.println("Error: Invalid transaction type '" + type + "'. Must be 'Deposit' or 'Withdrawal'.");
            return false;
        }
    }

    @Override
    public Customer findCustomerById(int id) {
        return customers.get(id);
    }

    @Override
    public Account findAccountById(int id) {
        return accounts.get(id);
    }

    @Override
    public Transaction findTransactionById(int id) {
        return transactions.get(id);
    }

    @Override
    public Beneficiary findBeneficiaryById(int id) {
        return beneficiaries.get(id);
    }

    @Override
    public List<Account> getAccountsByCustomerId(int customerId) {
        List<Account> result = new ArrayList<>();
        for (Account account : accounts.values()) {
            if (account.getCustomerID() == customerId) {
                result.add(account);
            }
        }
        return result;
    }

    @Override
    public List<Transaction> getTransactionsByAccountId(int accountId) {
        List<Transaction> result = new ArrayList<>();
        for (Transaction transaction : transactions.values()) {
            if (transaction.getAccountID() == accountId) {
                result.add(transaction);
            }
        }
        return result;
    }

    @Override
    public List<Beneficiary> getBeneficiariesByCustomerId(int customerId) {
        List<Beneficiary> result = new ArrayList<>();
        for (Beneficiary beneficiary : beneficiaries.values()) {
            if (beneficiary.getCustomerID() == customerId) {
                result.add(beneficiary);
            }
        }
        return result;
    }

    @Override
    public Collection<Account> getAllAccounts() {
        return accounts.values();
    }

    @Override
    public Collection<Customer> getAllCustomers() {
        return customers.values();
    }

    @Override
    public Collection<Transaction> getAllTransactions() {
        return transactions.values();
    }

    @Override
    public Collection<Beneficiary> getAllBeneficiaries() {
        return beneficiaries.values();
    }
}

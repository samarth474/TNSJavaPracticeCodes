package banking.service;

import banking.model.Account;
import banking.model.Beneficiary;
import banking.model.Customer;
import banking.model.Transaction;

import java.util.Collection;
import java.util.List;

/**
 * BankingService interface defining business operations for the banking system.
 */
public interface BankingService {

    boolean addCustomer(Customer customer);

    boolean addAccount(Account account);

    boolean addTransaction(Transaction transaction);

    boolean addBeneficiary(Beneficiary beneficiary);

    Customer findCustomerById(int id);

    Account findAccountById(int id);

    Transaction findTransactionById(int id);

    Beneficiary findBeneficiaryById(int id);

    List<Account> getAccountsByCustomerId(int customerId);

    List<Transaction> getTransactionsByAccountId(int accountId);

    List<Beneficiary> getBeneficiariesByCustomerId(int customerId);

    Collection<Account> getAllAccounts();

    Collection<Customer> getAllCustomers();

    Collection<Transaction> getAllTransactions();

    Collection<Beneficiary> getAllBeneficiaries();
}

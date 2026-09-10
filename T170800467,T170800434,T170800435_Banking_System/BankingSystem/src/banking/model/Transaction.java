package banking.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a financial transaction performed on a bank account.
 */
public class Transaction {
    private int transactionID;
    private int accountID;
    private String type; // Deposit or Withdrawal
    private double amount;
    private LocalDateTime timestamp;

    public Transaction(int transactionID, int accountID, String type, double amount) {
        this.transactionID = transactionID;
        this.accountID = accountID;
        this.type = type;
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
    }

    public Transaction(int accountID, String type, double amount) {
        this(0, accountID, type, amount);
    }

    public int getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTimestamp = (timestamp != null) ? timestamp.format(formatter) : "N/A";
        return "Transaction [" +
                "ID=" + transactionID +
                ", Account ID=" + accountID +
                ", Type='" + type + '\'' +
                ", Amount=" + String.format("%.2f", amount) +
                ", Timestamp=" + formattedTimestamp +
                ']';
    }
}

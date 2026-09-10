# Banking System Application (Core Java)

A standalone, console-based Banking System application written strictly using Core Java and object-oriented principles. The system allows users to manage Customers, Accounts, Beneficiaries, and Financial Transactions in memory using the Java Collections Framework.

---

## 🎯 Objective

The objective of this application is to demonstrate core Object-Oriented Programming (OOP) concepts, contract-based programming via Interfaces, and effective usage of the Java Collections Framework (`HashMap`, `ArrayList`, `List`, `Collection`) without relying on external frameworks, ORMs, or database engines.

---

## ✨ Features

1. **Customer Management**: Add customer profiles with ID, Name, Address, and Contact information.
2. **Account Management**: Create Savings or Current bank accounts associated with existing customers and track initial and running balances.
3. **Beneficiary Management**: Register beneficiaries linked to existing customers with account numbers and bank details.
4. **Transaction Processing**:
   - Support for **Deposit** and **Withdrawal** operations.
   - Automatic `LocalDateTime` timestamp recording.
   - Unique auto-incrementing transaction IDs.
   - Immediate account balance updates upon transaction completion.
   - Insufficient balance protection for withdrawals.
5. **Search & Lookup**:
   - Find customer details by Customer ID.
   - List all accounts owned by a specific customer.
   - View transaction history for an account.
   - List all beneficiaries attached to a customer.
6. **Input Validation & Safety**:
   - Prevents duplicate entity IDs.
   - Enforces non-negative balances and positive transaction amounts.
   - Validates existence of parent entities (e.g., Customer must exist before creating an Account or adding a Beneficiary).
   - Gracefully handles non-numeric and invalid console inputs without application crashes.

---

## 🛠️ Technology Used

- **Language**: Java 17+ (Core Java)
- **Frameworks / Libraries**: None (No Spring Boot, Hibernate, MySQL, MongoDB, or external dependencies)
- **Input Handling**: `java.util.Scanner`
- **Date & Time API**: `java.time.LocalDateTime`, `java.time.format.DateTimeFormatter`
- **Data Structures**: Java Collections (`HashMap`, `ArrayList`, `List`, `Collection`)

---

## 📁 Project Structure

```
BankingSystem/
├── src/
│   └── banking/
│       ├── model/
│       │   ├── Customer.java
│       │   ├── Account.java
│       │   ├── Transaction.java
│       │   └── Beneficiary.java
│       │
│       ├── service/
│       │   ├── BankingService.java
│       │   └── BankingServiceImpl.java
│       │
│       └── BankingSystemApp.java
│
├── bin/ (Compiled bytecode directory)
└── README.md
```

---

## 🧬 OOP Concepts Demonstrated

1. **Encapsulation**: All class attributes in `Customer`, `Account`, `Transaction`, and `Beneficiary` are declared `private` and accessed strictly via public getter and setter methods.
2. **Abstraction**: The business service contract is defined in the `BankingService` interface, hiding underlying storage and implementation details from `BankingSystemApp`.
3. **Polymorphism**: The application instantiates the service using interface reference polymorphism:
   ```java
   BankingService bankingService = new BankingServiceImpl();
   ```
4. **Method Overriding**: Overridden `toString()` methods in all domain model classes provide clean, readable representations of entity states.
5. **Separation of Concerns**: UI input/output logic is handled entirely in `BankingSystemApp`, while business validation and storage logic reside in `BankingServiceImpl`.

---

## 📦 Collection Concepts Demonstrated

- **`Map<Integer, T>` (`HashMap`)**: Used inside `BankingServiceImpl` for $O(1)$ fast lookups by entity IDs:
  ```java
  private Map<Integer, Customer> customers = new HashMap<>();
  private Map<Integer, Account> accounts = new HashMap<>();
  private Map<Integer, Transaction> transactions = new HashMap<>();
  private Map<Integer, Beneficiary> beneficiaries = new HashMap<>();
  ```
- **`List<T>` / `ArrayList<T>`**: Used to collect and return filtered views of accounts, transactions, and beneficiaries matching specific customer or account IDs.
- **`Collection<T>`**: Used as return types for `getAll...()` methods to return snapshot views of stored values (`map.values()`).

---

## 🚀 How to Run the Application

### Prerequisites
- JDK 17 or higher installed on your machine (`javac` and `java` available in PATH).

### Steps

1. **Navigate to the Project Root**:
   ```bash
   cd BankingSystem
   ```

2. **Compile the Java Sources**:
   ```bash
   mkdir -p bin
   javac -d bin src/banking/model/*.java src/banking/service/*.java src/banking/*.java
   ```

3. **Run the Application**:
   ```bash
   java -cp bin banking.BankingSystemApp
   ```

---

## 📋 Sample Menu & Output

```
================================
        BANKING SYSTEM          
================================
1. Add Customer
2. Add Account
3. Add Beneficiary
4. Add Transaction
5. Find Customer by ID
6. List All Accounts of Customer
7. List All Transactions of Account
8. List All Beneficiaries of Customer
9. Exit
================================
Enter your choice: 1

--- Add Customer ---
Enter Customer ID: 101
Enter Name: John Doe
Enter Address: 123 Main St
Enter Contact: 555-1234
Customer added successfully.

================================
        BANKING SYSTEM          
================================
...
Enter your choice: 4

--- Add Transaction ---
Enter Account ID: 1001
Enter Transaction Type (Deposit/Withdrawal): Deposit
Enter Amount: 500.00
Deposit successful!
Transaction ID: 1
Updated Account Balance: $1500.00
```

---

## 🧪 Verified Test Scenarios

| Test Case | Inputs / Action | Expected Result | Result |
|---|---|---|---|
| **1. Add Customer** | ID: 101, Name: John Doe | Customer added successfully | PASS |
| **2. Add Account** | Account: 1001, Customer: 101, Balance: 1000.0 | Account linked to Customer 101 | PASS |
| **3. Add Beneficiary** | Beneficiary: 501, Customer: 101, Name: Jane Doe | Beneficiary stored | PASS |
| **4. Deposit Money** | Account: 1001, Amount: 500.0 | Balance updated to $1500.00 | PASS |
| **5. Withdraw Money** | Account: 1001, Amount: 300.0 | Balance updated to $1200.00 | PASS |
| **6. Insufficient Withdrawal** | Account: 1001, Amount: 5000.0 | Transaction rejected with balance error | PASS |
| **7. Find Customer** | ID: 101 | Displays customer information | PASS |
| **8. List Customer Accounts** | Customer ID: 101 | Displays Account 1001 details | PASS |
| **9. List Transactions** | Account ID: 1001 | Displays Deposit & Withdrawal with timestamps | PASS |
| **10. List Beneficiaries** | Customer ID: 101 | Displays Beneficiary 501 | PASS |
| **11. Invalid Customer ID** | Account creation for non-existent Customer 999 | Rejected with error | PASS |
| **12. Duplicate Customer ID** | Adding Customer ID 101 again | Rejected with duplicate ID error | PASS |

---

## 🔮 Future Enhancements

- **File Persistence**: Persist data locally to JSON/CSV files using Java I/O (`java.nio.file`).
- **Account Transfer**: Direct fund transfers between two internal bank accounts.
- **Interest Calculation**: Add interest calculation strategy for Savings accounts.
- **Transaction Filtering**: Filter transactions by date range using `LocalDateTime`.

package atminterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Transaction {
    private String type;
    private double amount;
    private String Account;

    public Transaction(String type, double amount, String targetAccount) {
        this.type = type;
        this.amount = amount;
        this.Account = targetAccount;
    }

    @Override
    public String toString() {
        return type + " to " + Account + ": $" + amount;
    }
}

class Account {
    private String accountNumber;
    private double balance;
    private List<Transaction> transactionHistory;

    public Account(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
        this.transactionHistory = new ArrayList<>();
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add(new Transaction("Deposit", amount, accountNumber));
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.add(new Transaction("Withdrawal", amount, accountNumber));
        }
    }

    public void transfer(String targetAccountNumber, double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.add(new Transaction("Transfer", amount, targetAccountNumber));
        }
    }

    public List<Transaction> getTransactionHistory() {
        return transactionHistory;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }
}

public class ATM {
    public static void main(String[] args) {
        Account account = new Account("39452934752", 0);

        try (Scanner scanner = new Scanner(System.in)) {
			while (true) {
			    System.out.println("\n1. Deposit");
			    System.out.println("2. Withdraw");
			    System.out.println("3. Transfer");
			    System.out.println("4. View Transaction History");
			    System.out.println("5. View Balance");
			    System.out.println("6. Exit");
			    System.out.println("===============================");
			    System.out.println("Choose an option:");


			    int choice = scanner.nextInt();
			    scanner.nextLine(); 

			    switch (choice) {
			        case 1:
			            System.out.print("Enter deposit amount: $");
			            double depositAmount = scanner.nextDouble();
			            account.deposit(depositAmount);
			            System.out.println("Amount deposited successfully.");
			            System.out.println("New balance: $" + account.getBalance());
			            System.out.println("===============================");
			            break;
			        case 2:
			            System.out.print("Enter withdrawal amount: $");
			            double withdrawalAmount = scanner.nextDouble();
			            account.withdraw(withdrawalAmount);
			            System.out.println("Amount withdrawal successfully.");
			            System.out.println("New balance: $" + account.getBalance());
			            System.out.println("===============================");
			            break;
			        case 3:
			            System.out.print("Enter account number: ");
			            String targetAccountNumber = scanner.next();
			            scanner.nextLine(); // Consume the newline character
			            System.out.print("Enter transfer amount: $");
			            double transferAmount = scanner.nextDouble();
			            account.transfer(targetAccountNumber, transferAmount);
			            System.out.println("Amount transfered successfully.");
			            System.out.println("New balance: $" + account.getBalance());
			            System.out.println("===============================");
			            break;
			        case 4:
			            List<Transaction> transactions = account.getTransactionHistory();
			            System.out.println("Transaction History :");
			            System.out.println("===================");
			            for (Transaction transaction : transactions) {
			                System.out.println(transaction);
			            }
			            System.out.println("================================");
			            break;
			        case 5:
			            System.out.println("Account Balance: $" + account.getBalance());
			            System.out.println("===============================");
			            break;
			        case 6:
			        	System.out.println("********************************************************");
			            System.out.println("                   Thank you ...!");
			            System.out.println("********************************************************");
			            System.exit(0);
			            break;
			        default:
			            System.out.println("Invalid option. Please try again.");
			    }
			}
		}
    }
}

package org.example;


import org.example.model.Bank;
import org.example.model.Account;



import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.example.model.Transaction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        displayWelcomeMessage();
        Scanner scanner = new Scanner(System.in);
        List<Bank> banks = new ArrayList<>();
        Bank currentBank = null;

        for (;;) {
            showMenuOptions();
            int choice = getUserChoice(scanner);

            switch (choice) {
                case 0:
                    terminateProgram();
                    break;
                case 1:
                    currentBank = establishNewBank(scanner, banks);
                    break;
                case 2:
                    createNewAccount(scanner, currentBank);
                    break;
                case 3:
                    handleTransaction(scanner, currentBank);
                    break;
                case 4:
                    handleWithdrawal(scanner, currentBank);
                    break;
                case 5:
                    handleDeposit(scanner, currentBank);
                    break;
                case 6:
                    browseAccountTransactions(scanner, currentBank);
                    break;
                case 7:
                    checkAccountBalance(scanner, currentBank);
                    break;
                case 8:
                    displayBankAccounts(banks);
                    break;
                case 9:
                    verifyTotalTransactionFee(currentBank);
                    break;
                case 10:
                    verifyTotalTransferAmount(currentBank);
                    break;
                default:
                    System.out.println("Invalid selection. Please try again.");
                    break;
            }
        }
    }

    private static void displayWelcomeMessage() {
        System.out.println("\n\n ____BANK_SYSTEM1____");
    }

    private static void showMenuOptions() {
        System.out.println("""
                Please select an option:
                1 - Establish a new bank
                2 - Open an account
                3 - Initiate a transaction
                4 - Withdraw funds
                5 - Deposit funds
                6 - View account transactions
                7 - View account balance
                8 - Display bank accounts
                9 - Verify total transaction fees
                10 - Verify total transfer amounts
                0 - Exit
                """);
    }

    private static int getUserChoice(Scanner scanner) {
        System.out.print("Enter your choice: ");
        return scanner.nextInt();
    }

    private static Bank establishNewBank(Scanner scanner, List<Bank> banks) {
        System.out.println("********************************************");
        System.out.print("Enter bank name: ");
        scanner.nextLine();
        String bankName = scanner.nextLine();
        Bank newBank = Bank.createInstitution(bankName, new ArrayList<>());
        banks.add(newBank);
        System.out.println("Bank successfully established: " + bankName);
        System.out.println("********************************************");
        return newBank;
    }

    private static void createNewAccount(Scanner scanner, Bank bank) {
        if (bank == null) {
            System.out.println("No banks available yet.");
            return;
        }
        System.out.println("********************************************");
        System.out.print("Enter account holder's name: ");
        scanner.nextLine();
        String accountHolderName = scanner.nextLine();
        System.out.print("Enter initial account balance: ");
        double balance = scanner.nextDouble();
        Account account = new Account(bank.getAccounts().size() + 1, accountHolderName, balance);
        bank.addAccount(account);
        System.out.println("Account successfully created. Account ID: " + account.getId());
        System.out.println("********************************************");
    }

    private static void handleTransaction(Scanner scanner, Bank bank) {
        if (bank == null || bank.getAccounts().isEmpty()) {
            System.out.println("********************************************");
            System.out.println("No bank or accounts created yet.");
            return;
        }
        try {
            System.out.println("********************************************");
            System.out.print("Enter your account ID: ");
            int accountId = scanner.nextInt();
            System.out.print("Enter the amount you want to transfer: ");
            double amount = scanner.nextDouble();

            Account sender = bank.getAccounts().stream()
                    .filter(acc -> acc.getId() == accountId)
                    .findFirst()
                    .orElse(null);

            if (sender == null) {
                System.out.println("Account not found.");
                return;
            }

            Transaction transaction = new Transaction(amount, 0, 0, "", 0);

            if (!transaction.isTransactionPossible(sender.getCurrentBalance(), amount)) {
                System.out.println("Insufficient balance to perform this transaction!");
                return;
            }

            System.out.print("Enter fee type (1 for flat fee, 2 for percentage fee): ");
            int feeType = scanner.nextInt();
            double fee = feeType == 1 ? transaction.calculateFee(amount, 10, 0, feeType) : transaction.calculateFee(amount, 0, 5, feeType);

            System.out.print("Enter the account ID you want to transfer to: ");
            int recipientAccountId = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter the reason of the transfer: ");
            String message = scanner.nextLine();

            Account recipient = bank.getAccounts().stream()
                    .filter(acc -> acc.getId() == recipientAccountId)
                    .findFirst()
                    .orElse(null);

            if (recipient == null) {
                System.out.println("Recipient account not found.");
                return;
            }

            sender.withdrawAmount(amount + fee);
            recipient.depositAmount(amount);
            transaction.setTransactionAmount(sender.getId());
            transaction.setTransactionFee(recipient.getId());
            transaction.setReason(message);
            sender.recordTransaction(transaction);
            recipient.recordTransaction(transaction);

            System.out.println("Transaction success! New balance in " + sender.getHolderName() + ": " + sender.getCurrentBalance());
            System.out.println("New balance in " + recipient.getHolderName() + ": " + recipient.getCurrentBalance());
            System.out.println("********************************************");
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private static void handleWithdrawal(Scanner scanner, Bank bank) {

        if (bank == null || bank.getAccounts().isEmpty()) {
            System.out.println("No bank or accounts created yet.");
            return;
        }
        try {
            System.out.println("********************************************");
            System.out.print("Enter your account ID: ");
            int accountId = scanner.nextInt();

            Account account = bank.getAccounts().stream()
                    .filter(acc -> acc.getId() == accountId)
                    .findFirst()
                    .orElse(null);

            if (account == null) {
                System.out.println("Account not found.");
                return;
            }

            System.out.print("Enter the amount you want to withdraw: ");
            double amount = scanner.nextDouble();

            if (amount <= 0) {
                System.out.println("Invalid amount.");
                return;
            }

            if (!account.hasSufficientBalance(amount)) {
                System.out.println("Insufficient balance to perform this withdrawal!");
                return;
            }

            account.withdrawAmount(amount);
            System.out.println("Withdrawal success! New balance: " + account.getCurrentBalance());
            System.out.println("********************************************");
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private static void handleDeposit(Scanner scanner, Bank bank) {

        if (bank == null || bank.getAccounts().isEmpty()) {
            System.out.println("No bank or accounts created yet.");
            return;
        }
        try {
            System.out.println("********************************************");
            System.out.print("Enter your account ID: ");
            int accountId = scanner.nextInt();

            Account account = bank.getAccounts().stream()
                    .filter(acc -> acc.getId() == accountId)
                    .findFirst()
                    .orElse(null);

            if (account == null) {
                System.out.println("Account not found.");
                return;
            }

            System.out.print("Enter the amount you want to deposit: ");
            double amount = scanner.nextDouble();

            if (amount <= 0) {
                System.out.println("Invalid amount.");
                return;
            }

            account.depositAmount(amount);
            System.out.println("Deposit success! New balance: " + account.getCurrentBalance());
            System.out.println("********************************************");
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private static void browseAccountTransactions(Scanner scanner, Bank bank) {
        if (bank == null) {
            System.out.println("No bank created yet.");
            return;
        }
        try {
            System.out.print("Enter the account ID to view transactions: ");
            int accountId = scanner.nextInt();

            Account account = bank.getAccounts().stream()
                    .filter(acc -> acc.getId() == accountId)
                    .findFirst()
                    .orElse(null);

            if (account == null) {
                System.out.println("Account not found.");
                return;
            }

            List<Transaction> transactions = account.getTransactionHistory();
            if (transactions.isEmpty()) {
                System.out.println("No transactions found for this account.");
            } else {
                System.out.println("********************************************");
                System.out.println("Transactions for account " + account.getHolderName() + ":");
                transactions.forEach(transaction -> {
                    System.out.println("Amount: " + transaction.getTransactionAmount());
                    System.out.println("Sender account ID: " + transaction.getTransactionFee());
                    System.out.println("Recipient account ID: " + transaction.getSenderAccountId());
                    System.out.println("Reason of the transfer : " + transaction.getReason());
                    System.out.println("********************************************");
                });
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }

    }

    private static void checkAccountBalance(Scanner scanner, Bank bank) {

        if (bank == null) {
            System.out.println("No bank created yet.");
            return;
        }
        try {
            System.out.println("********************************************");
            System.out.print("Enter the account ID to view total balance: ");
            int accountId = scanner.nextInt();

            Account account = bank.getAccounts().stream()
                    .filter(acc -> acc.getId() == accountId)
                    .findFirst()
                    .orElse(null);

            if (account == null) {
                System.out.println("Account not found.");
                return;
            }

            System.out.println("Balance for account " + account.getHolderName() + ": " + account.getCurrentBalance());
            System.out.println("********************************************");
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private static void displayBankAccounts(List<Bank> banks) {
        if (banks.isEmpty()) {
            System.out.println("No banks created yet.");
            return;
        }
        try {
            System.out.println("********************************************");
            banks.forEach(bank -> {
                List<Account> accounts = bank.getAccounts();
                if (accounts.isEmpty()) {
                    System.out.println("No accounts found in the bank: " + bank.getName());
                } else {
                    System.out.println("List of " + bank.getName() + " bank accounts:");
                    accounts.forEach(account ->
                            System.out.println("Account ID: " + account.getId() +
                                    ", Name: " + account.getHolderName() +
                                    ", Balance: " + account.getCurrentBalance() +
                                    ", Bank name: " + bank.getName()));
                }
            });
            System.out.println("********************************************");
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }

    }

    private static void verifyTotalTransactionFee(Bank bank) {

    }

    private static void verifyTotalTransferAmount(Bank bank) {

    }

    private static void terminateProgram() {
        System.out.println("Exiting program.");
        System.exit(0);
    }
}

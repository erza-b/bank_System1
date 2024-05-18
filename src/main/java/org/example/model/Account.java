package org.example.model;

import org.example.interfaces.AccountInt;
import java.util.ArrayList;
import java.util.List;

public class Account implements AccountInt {

    private int id;
    private String holderName;
    private double currentBalance;
    private List<Transaction> transactionHistory;

    public Account(int id, String holderName, double initialBalance) {
        this.id = id;
        this.holderName = holderName;
        this.currentBalance = initialBalance;
        this.transactionHistory = new ArrayList<>();
    }

    public String displayAccountDetails() {
        return "Account Holder: " + holderName + ", Current Balance: $" + currentBalance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(double currentBalance) {
        this.currentBalance = currentBalance;
    }

    public void withdrawAmount(double amount) {
        if (currentBalance >= amount) {
            currentBalance -= amount;
        } else {
            System.out.println("Insufficient funds: Current balance is $" + currentBalance + ", withdrawal amount: $" + amount);
        }
    }

    public void depositAmount(double amount) {
        currentBalance += amount;
        System.out.println("Deposited: $" + amount);
    }

    public boolean hasSufficientBalance(double amount) {
        return currentBalance >= amount;
    }

    public void recordTransaction(Transaction transaction) {
        transactionHistory.add(transaction);
    }

    public List<Transaction> getTransactionHistory() {
        return transactionHistory;
    }
}

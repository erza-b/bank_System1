package org.example.model;

import java.util.List;

public class Bank {
    private String name;
    private List<Account> accounts;
    private double totalFeesCollected;
    private double totalTransferredAmount;
    private double flatFeePerTransaction;
    private double percentFeePerTransaction;

    public Bank(String name, List<Account> accounts, double totalFeesCollected, double totalTransferredAmount, double flatFeePerTransaction, double percentFeePerTransaction) {
        this.name = name;
        this.accounts = accounts;
        this.totalFeesCollected = totalFeesCollected;
        this.totalTransferredAmount = totalTransferredAmount;
        this.flatFeePerTransaction = flatFeePerTransaction;
        this.percentFeePerTransaction = percentFeePerTransaction;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public double getTotalFeesCollected() {
        return totalFeesCollected;
    }

    public void setTotalFeesCollected(double totalFeesCollected) {
        this.totalFeesCollected = totalFeesCollected;
    }

    public double getTotalTransferredAmount() {
        return totalTransferredAmount;
    }

    public void setTotalTransferredAmount(double totalTransferredAmount) {
        this.totalTransferredAmount = totalTransferredAmount;
    }

    public double getFlatFeePerTransaction() {
        return flatFeePerTransaction;
    }

    public void setFlatFeePerTransaction(double flatFeePerTransaction) {
        this.flatFeePerTransaction = flatFeePerTransaction;
    }

    public double getPercentFeePerTransaction() {
        return percentFeePerTransaction;
    }

    public void setPercentFeePerTransaction(double percentFeePerTransaction) {
        this.percentFeePerTransaction = percentFeePerTransaction;
    }

    public static Bank createInstitution(String name, List<Account> accounts) {
        Bank institution = new Bank(name, accounts, 0, 0, 5.0, 2.0);
        System.out.println("Bank created: " + name);
        return institution;
    }
}

package org.example.model;

import org.example.interfaces.TransactionInt;

public class Transaction implements TransactionInt {

    private double transactionAmount;
    private int senderAccountId;
    private int receiverAccountId;
    private String reason;
    private double transactionFee;

    public Transaction(double transactionAmount, int senderAccountId, int receiverAccountId, String reason, double transactionFee) {
        this.transactionAmount = transactionAmount;
        this.senderAccountId = senderAccountId;
        this.receiverAccountId = receiverAccountId;
        this.reason = reason;
        this.transactionFee = transactionFee;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public double getTransactionFee() {
        return transactionFee;
    }

    public void setTransactionFee(double transactionFee) {
        this.transactionFee = transactionFee;
    }

    public int getSenderAccountId() {
        return senderAccountId;
    }

    public void setSenderAccountId(int senderAccountId) {
        this.senderAccountId = senderAccountId;
    }

    public int getReceiverAccountId() {
        return receiverAccountId;
    }

    public void setReceiverAccountId(int receiverAccountId) {
        this.receiverAccountId = receiverAccountId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public double calculateFee(double amount, double flatFee, double percentFee, int feeType) {
        switch (feeType) {
            case 1:
                System.out.println("Flat fee applied: $" + flatFee);
                return flatFee;
            case 2:
                System.out.println("Percent fee applied: " + percentFee + "%");
                return (percentFee / 100) * amount;
            default:
                System.out.println("Invalid fee type. Applying default flat fee.");
                return flatFee;
        }
    }

    @Override
    public String getTransactionType() {
        return "Standard Transaction";
    }

    public static boolean isTransactionPossible(double balance, double amount) {
        return balance >= amount;
    }
}

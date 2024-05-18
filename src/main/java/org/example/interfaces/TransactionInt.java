package org.example.interfaces;

public interface TransactionInt {
    double calculateFee(double amount, double flatFee, double percentFee, int feeType);

    String getTransactionType();
}

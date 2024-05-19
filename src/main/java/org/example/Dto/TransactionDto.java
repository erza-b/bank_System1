package org.example.Dto;

public class TransactionDto {
    private double amount;
    private int originatingAccountId;
    private int resultingAccountId;
    private String transactionReason;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getOriginatingAccountId() {
        return originatingAccountId;
    }

    public void setOriginatingAccountId(int originatingAccountId) {
        this.originatingAccountId = originatingAccountId;
    }

    public int getResultingAccountId() {
        return resultingAccountId;
    }

    public void setResultingAccountId(int resultingAccountId) {
        this.resultingAccountId = resultingAccountId;
    }

    public String getTransactionReason() {
        return transactionReason;
    }

    public void setTransactionReason(String transactionReason) {
        this.transactionReason = transactionReason;
    }
}


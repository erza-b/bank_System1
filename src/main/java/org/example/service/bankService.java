package org.example.service;

import org.example.Dto.AccountDto;
import org.example.Dto.TransactionDto;
import org.example.model.Account;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class bankService {

    private final List<Account> accounts = new ArrayList<>();
    private int accountIdCounter = 1;

    public AccountDto createAccount(AccountDto accountDto) {
        Account account = new Account(accountIdCounter++, accountDto.getName(), accountDto.getBalance());
        accounts.add(account);
        return accountDto;
    }

    public TransactionDto performTransaction(TransactionDto transactionDto) {
        Account originatingAccount = findAccountById(transactionDto.getOriginatingAccountId());
        Account resultingAccount = findAccountById(transactionDto.getResultingAccountId());

        if (originatingAccount == null || resultingAccount == null) {
            throw new IllegalArgumentException("Invalid account ID");
        }

        if (originatingAccount.getCurrentBalance() < transactionDto.getAmount()) {
            throw new IllegalArgumentException("Insufficient balance for transaction");
        }

        originatingAccount.withdrawAmount(transactionDto.getAmount());
        resultingAccount.depositAmount(transactionDto.getAmount());

        return transactionDto;
    }

    private Account findAccountById(int accountId) {
        return accounts.stream()
                .filter(account -> account.getId() == accountId)
                .findFirst()
                .orElse(null);
    }
}

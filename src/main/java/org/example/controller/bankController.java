package org.example.controller;

import org.example.Dto.AccountDto;
import org.example.Dto.TransactionDto;
import org.example.service.bankService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class bankController {
    private final bankService BankService;
    private int accountIdCounter = 1;

    public bankController(bankService bankService) {
        this.BankService = bankService;
    }

    @PostMapping("/account")
    public ResponseEntity<?> createAccount(@RequestBody AccountDto accountDto) {
        try {
            accountDto.setAccountId(accountIdCounter++);

            AccountDto createdAccount = BankService.createAccount(accountDto);

            Map<String, Object> response = new HashMap<>();
            response.put("name", createdAccount.getName());
            response.put("balance", createdAccount.getBalance());

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to create account: " + e.getMessage());
        }
    }


    @PostMapping("/transactions")
    public ResponseEntity<?> performTransaction(@RequestBody TransactionDto transactionDto) {
        try {
            TransactionDto createdTransaction = BankService.performTransaction(transactionDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdTransaction);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error performing transaction: " + e.getMessage());
        }
    }
}

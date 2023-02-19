package com.assessement.code.account.api;

import com.assessement.code.account.entity.Account;
import com.assessement.code.account.entity.Transaction;
import com.assessement.code.account.exception.AccountNotFoundException;
import com.assessement.code.account.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
public class AccountController {


    private final AccountService accountsService;

    private static final Logger log = LoggerFactory.getLogger(AccountController.class);

    public AccountController(AccountService accountsService) {
        this.accountsService = accountsService;
    }

    @GetMapping(value = "/{customerId}/accounts")
    public List<Account> getAccountList(@PathVariable("customerId") Long customerId) throws AccountNotFoundException {
        List<Account> accounts = accountsService.getAllAccountsForCustomer(customerId);
        if (accounts.isEmpty()) {
            log.info("No accounts mapped for the user : {}", customerId);
            throw new AccountNotFoundException(customerId);
        }
        log.info("Returning account : {} and size : {}", accounts, accounts.size());
        return accounts;
    }

    @GetMapping(value = "/{customerId}/accounts/{accountId}/transactions")
    public List<Transaction> getTransactionsForAccount(@PathVariable("accountId") Long accountId) {
        List<Transaction> accounts = accountsService.getTransactionsForAccount(accountId);
        if (accounts.isEmpty()) {
            log.info("No accounts mapped for the user : {}", accountId);
        }
        log.info("Returning account : {} and size : {}", accounts, accounts.size());
        return accounts;
    }
}

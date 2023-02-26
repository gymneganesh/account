package com.assessement.code.account.api;

import com.assessement.code.account.dto.AccountDto;
import com.assessement.code.account.dto.AccountTransactionDto;
import com.assessement.code.account.exception.AccountNotFoundException;
import com.assessement.code.account.exception.TransactionNotFoundException;
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

    private static final Logger log = LoggerFactory.getLogger(AccountController.class);
    private final AccountService accountsService;

    public AccountController(AccountService accountsService) {
        this.accountsService = accountsService;
    }

    @GetMapping(value = "/{customerId}/accounts")
    public List<AccountDto> getAccountList(@PathVariable("customerId") Long customerId) throws AccountNotFoundException {
        final List<AccountDto> accounts = accountsService.getAllAccountsForCustomer(customerId);
        if (accounts.isEmpty()) {
            log.info("No accounts mapped for the customer : {}", customerId);
            throw new AccountNotFoundException(customerId);
        }
        log.info("found : {}  account for the customer: {}", accounts.size(), customerId);
        return accounts;
    }

    @GetMapping(value = "/{customerId}/accounts/{accountId}/transactions")
    public List<AccountTransactionDto> getTransactionsForAccount(
            @PathVariable("customerId") Long customerId,
            @PathVariable("accountId") Long accountId) {
        final List<AccountTransactionDto> accountTransactions = accountsService.getTransactionsForAccount(customerId, accountId);
        if (accountTransactions.isEmpty()) {
            log.info("No transactions available for the account : {}", accountId);
            throw new TransactionNotFoundException(accountId);
        }
        log.info("Returning transactions list for the account : {}", accountId);
        return accountTransactions;
    }
}

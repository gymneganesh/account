package com.assessement.code.account.service;

import com.assessement.code.account.dto.AccountTransactionDto;
import com.assessement.code.account.entity.Account;
import com.assessement.code.account.entity.Transaction;

import java.util.List;

public interface AccountService {
    List<Account> getAllAccountsForCustomer(Long customerId);

    List<Transaction> getTransactionsForAccount(Long accountId);
}

package com.assessement.code.account.service;

import com.assessement.code.account.dao.AccountDao;
import com.assessement.code.account.dao.TransactionDao;
import com.assessement.code.account.entity.Account;
import com.assessement.code.account.entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private TransactionDao transactionDao;

    public List<Account> getAllAccountsForCustomer(Long customerId) {

        return accountDao.findByCustomerId(customerId);
    }

    @Override
    public List<Transaction> getTransactionsForAccount(Long accountId) {
        return transactionDao.findByAccountId(accountId);
    }
}

package com.assessement.code.account.service;

import com.assessement.code.account.dao.AccountDao;
import com.assessement.code.account.dao.TransactionDao;
import com.assessement.code.account.dto.AccountDto;
import com.assessement.code.account.dto.AccountTransactionDto;
import com.assessement.code.account.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountDao accountDao;

    private final TransactionDao transactionDao;

    public AccountServiceImpl(AccountDao accountDao, TransactionDao transactionDao) {
        this.accountDao = accountDao;
        this.transactionDao = transactionDao;
    }

    public List<AccountDto> getAllAccountsForCustomer(Long customerId) {
        return accountDao.findByCustomerId(customerId);
    }

    @Override
    public List<AccountTransactionDto> getTransactionsForAccount(Long customerId, Long accountId) {
        return transactionDao.findTransByAccountId(customerId, accountId);
    }
}

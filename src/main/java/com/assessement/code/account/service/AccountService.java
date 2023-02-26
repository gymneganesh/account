package com.assessement.code.account.service;

import com.assessement.code.account.dto.AccountDto;
import com.assessement.code.account.dto.AccountTransactionDto;

import java.util.List;

public interface AccountService {

    List<AccountDto> getAllAccountsForCustomer(Long customerId);

    List<AccountTransactionDto> getTransactionsForAccount(Long customerId, Long accountId);
}

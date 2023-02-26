package com.assessement.code.account.service;


import com.assessement.code.account.dao.AccountDao;
import com.assessement.code.account.dao.TransactionDao;
import com.assessement.code.account.dto.AccountDto;
import com.assessement.code.account.dto.AccountTransactionDto;
import com.assessement.code.account.entity.AccountType;
import com.assessement.code.account.entity.TransactionType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AccountServiceImplTest {

    @InjectMocks
    AccountServiceImpl accountService;

    @Mock
    AccountDao accountDao;

    @Mock
    TransactionDao transactionDao;

    @Test
    public void testGetAllAccountsForValidCustomerId() {
        List<AccountDto> accountDtoList = Arrays.asList(new AccountDto(1001L, "Test account name", AccountType.SAVINGS, "AUD", BigDecimal.ZERO));
        when(accountDao.findByCustomerId(any())).thenReturn(accountDtoList);

        //test
        List<AccountDto> accountDtoList1 = accountService.getAllAccountsForCustomer(1001L);

        assertEquals(1, accountDtoList1.size());
        verify(accountDao, times(1)).findByCustomerId(any());
    }

    @Test
    public void testGetAllAccountsForCustomerWithNoAccounts() {
        List<AccountDto> accountDtoList = Collections.emptyList();
        when(accountDao.findByCustomerId(any())).thenReturn(accountDtoList);

        //test
        List<AccountDto> accountDtoList1 = accountService.getAllAccountsForCustomer(1001L);

        assertEquals(0, accountDtoList1.size());
        verify(accountDao, times(1)).findByCustomerId(any());
    }

    @Test
    public void testGetTransactionsForAnAccount() {
        List<AccountTransactionDto> accountTransactionDtos = Arrays.asList(
                new AccountTransactionDto(1001L, new Date(), BigDecimal.valueOf(1000L), "test desc", TransactionType.CREDIT, "Test account name", "AUD")
        );
        when(transactionDao.findTransByAccountId(any(), any())).thenReturn(accountTransactionDtos);

        //test
        List<AccountTransactionDto> accountTransactionDtos1 = accountService.getTransactionsForAccount(1001L, 200L);

        assertEquals(1, accountTransactionDtos1.size());
        verify(transactionDao, times(1)).findTransByAccountId(any(), any());
    }

    @Test
    public void testGetTransactionsForAnAccountWithNoTransactions() {
        List<AccountTransactionDto> accountTransactionDtos = Collections.emptyList();
        when(transactionDao.findTransByAccountId(any(), any())).thenReturn(accountTransactionDtos);

        //test
        List<AccountTransactionDto> accountTransactionDtos1 = accountService.getTransactionsForAccount(1001L, 200L);

        assertEquals(0, accountTransactionDtos1.size());
        verify(transactionDao, times(1)).findTransByAccountId(any(), any());
    }

}


package com.assessement.code.account.api;

import com.assessement.code.account.dto.AccountDto;
import com.assessement.code.account.dto.AccountTransactionDto;
import com.assessement.code.account.entity.AccountType;
import com.assessement.code.account.entity.TransactionType;
import com.assessement.code.account.service.AccountService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AccountController.class)
public class AccountControllerTest {

    @MockBean
    AccountService accountService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testGetAllAccountsForCustomer() throws Exception {

        List<AccountDto> accountDtos = Arrays.asList(new AccountDto(1001L, "Test account name", AccountType.SAVINGS, "AUD", BigDecimal.ZERO));
        Mockito.when(accountService.getAllAccountsForCustomer(any())).thenReturn(accountDtos);

        mockMvc.perform(get("/api/v1/1001/accounts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].currency", Matchers.is("AUD")))
                .andExpect(jsonPath("$[0].accountName", Matchers.is("Test account name")));
    }

    @Test
    public void testGetAllAccountForCustomerWithNoAccounts() throws Exception {

        List<AccountDto> accountDtos = Collections.emptyList();
        Mockito.when(accountService.getAllAccountsForCustomer(any())).thenReturn(accountDtos);

        mockMvc.perform(get("/api/v1/1001/accounts"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$", Matchers.aMapWithSize(2)))
                .andExpect(jsonPath("$.errorCode", Matchers.is(404)))
                .andExpect(jsonPath("$.errorMessage", Matchers.is("Couldn't find any accounts for the user 1001")));

    }

    @Test
    public void testGetTransactionsForAccount() throws Exception {
        List<AccountTransactionDto> accountDtos = Arrays.asList(
                new AccountTransactionDto(1001L, new Date(), BigDecimal.valueOf(1000L), "test desc", TransactionType.CREDIT, "Test account name", "AUD")
        );
        Mockito.when(accountService.getTransactionsForAccount(any(), any())).thenReturn(accountDtos);

        mockMvc.perform(get("/api/v1/1001/accounts/1001/transactions"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].currency", Matchers.is("AUD")))
                .andExpect(jsonPath("$[0].transactionType", Matchers.is(TransactionType.CREDIT.toString())));

    }

    @Test
    public void testAccountWithNoTransactions() throws Exception {

        List<AccountTransactionDto> transactionDtos = Collections.emptyList();
        Mockito.when(accountService.getTransactionsForAccount(any(), any())).thenReturn(transactionDtos);

        mockMvc.perform(get("/api/v1/100001/accounts/10001/transactions"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$", Matchers.aMapWithSize(2)))
                .andExpect(jsonPath("$.errorCode", Matchers.is(404)))
                .andExpect(jsonPath("$.errorMessage", Matchers.is("Couldn't find any transactions for the account 10001")));

    }
}

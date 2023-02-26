package com.assessement.code.account.exception;

import com.assessement.code.account.api.AccountController;
import com.assessement.code.account.service.AccountService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
public class AccountExceptionHandlerTest {
    @InjectMocks
    AccountController accountController;
    @Mock
    AccountService accountService;
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(accountController)
                .setControllerAdvice(new AccountExceptionHandler())
                .build();
    }

    @Test
    public void testAccountNotFoundExceptionHandler() throws Exception {
        Mockito.when(accountService.getAllAccountsForCustomer(any()))
                .thenThrow(new AccountNotFoundException(1001L));
        mockMvc.perform(get("/api/v1/1001/accounts"))
                .andExpect(status().is(404))
                .andExpect(jsonPath("$.errorMessage", Matchers.equalTo("Couldn't find any accounts for the user 1001")))
                .andExpect(jsonPath("$.errorCode", Matchers.equalTo(404)));

    }

    @Test
    public void testTransactionNotFoundExceptionHandler() throws Exception {
        Mockito.when(accountService.getTransactionsForAccount(any(), any()))
                .thenThrow(new TransactionNotFoundException(2001L));
        mockMvc.perform(get("/api/v1/1001/accounts/1001/transactions"))
                .andExpect(jsonPath("$.errorMessage", Matchers.equalTo("Couldn't find any transactions for the account 2001")))
                .andExpect(jsonPath("$.errorCode", Matchers.equalTo(404)));

    }
}


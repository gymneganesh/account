package com.assessement.code.account.dto;


import com.assessement.code.account.entity.AccountType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class AccountDto {
    private Long accountNumber;
    private String accountName;
    private AccountType accountType;
    private LocalDate balanceDate;
    private String currency;

    @Getter(AccessLevel.NONE)
    private BigDecimal balance;

    public AccountDto(Long accountNumber, String accountName, AccountType accountType, String currency, BigDecimal balance) {
        this.accountNumber = accountNumber;
        this.accountName = accountName;
        this.accountType = accountType;
        this.currency = currency;
        this.balance = balance;
        this.balanceDate = LocalDate.now();
    }

    public BigDecimal getBalance() {
        return this.balance == null ? BigDecimal.ZERO : this.balance;
    }
}

package com.assessement.code.account.dto;


import com.assessement.code.account.entity.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountTransactionDto {
    private Long accountId;
    private Date date;
    private BigDecimal amount;
    private String description;
    private TransactionType transactionType;
    private String accountName;
    private String currency;
}

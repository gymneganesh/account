package com.assessement.code.account.dao;

import com.assessement.code.account.dto.AccountTransactionDto;
import com.assessement.code.account.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface TransactionDao extends JpaRepository<Transaction, Long> {

    List<Transaction> findByAccountId(Long accountId);

    @Query("SELECT new com.assessement.code.account.dto.AccountTransactionDto(t.accountId,t.date,t.amount,t.description,t.type, a.accountName, a.currency) " +
            "from Transaction t " +
            "JOIN Account a on t.accountId = a.accountNumber " +
            "WHERE a.accountNumber=:accountId AND a.customerId = :customerId Order by t.date DESC")
    List<AccountTransactionDto> findTransByAccountId(@PathVariable("customerId") Long customerId, @PathVariable("accountId") Long accountId);

}

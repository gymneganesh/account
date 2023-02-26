package com.assessement.code.account.dao;

import com.assessement.code.account.dto.AccountDto;
import com.assessement.code.account.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface AccountDao extends JpaRepository<Account, Long> {

    @Query("SELECT new com.assessement.code.account.dto.AccountDto(a.accountNumber, a.accountName,a.accountType, a.currency, SUM(t.amount))" +
            "from Account a " +
            "LEFT JOIN Transaction t on t.accountId = a.accountNumber " +
            "WHERE a.customerId = :customerId group by t.accountId")
    List<AccountDto> findByCustomerId(@PathVariable("customerId") Long customerId);
}

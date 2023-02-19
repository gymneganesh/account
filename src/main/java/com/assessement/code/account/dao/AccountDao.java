package com.assessement.code.account.dao;

import com.assessement.code.account.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountDao extends JpaRepository<Account, Long> {
    List<Account> findByCustomerId(Long customerId);
}

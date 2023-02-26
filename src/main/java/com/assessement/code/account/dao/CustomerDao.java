package com.assessement.code.account.dao;

import com.assessement.code.account.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerDao extends JpaRepository<Customer, Long> {
    Optional<Customer> findById(Long id);
}

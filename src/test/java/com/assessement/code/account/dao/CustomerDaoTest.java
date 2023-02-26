package com.assessement.code.account.dao;

import com.assessement.code.account.dto.AccountDto;
import com.assessement.code.account.entity.Customer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;


@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase
public class CustomerDaoTest {
    @Autowired
    CustomerDao customerDao;

    @Test
    @Sql("/test.sql")
    public void testFindByCustomerId() {
        Optional<Customer> customer = customerDao.findById(1001L);
        Assertions.assertThat(customer.get().getId()).isEqualTo(1001L);
    }

    @Test
    public void testFindByUnknownCustomerId() {
        Optional<Customer> customer = customerDao.findById(10051L);
        Assertions.assertThat(customer.isPresent()).isEqualTo(false);
    }
}

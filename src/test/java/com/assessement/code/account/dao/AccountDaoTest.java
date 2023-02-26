package com.assessement.code.account.dao;

import com.assessement.code.account.dto.AccountDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;


@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase
public class AccountDaoTest {
    @Autowired
    AccountDao accountDao;

    @Test
    @Sql("/test.sql")
    public void testFindAllAccountsByValidCustomerId() {
        List<AccountDto> accountDtoList = accountDao.findByCustomerId(1001L);
        Assertions.assertThat(accountDtoList.size()).isEqualTo(3);
    }

    @Test
    public void testFindAllAccountsByUnknownCustomerId() {
        List<AccountDto> accountDtoList = accountDao.findByCustomerId(10014L);
        Assertions.assertThat(accountDtoList.size()).isEqualTo(0);
    }
}

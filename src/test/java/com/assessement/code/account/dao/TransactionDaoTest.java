package com.assessement.code.account.dao;

import com.assessement.code.account.dto.AccountDto;
import com.assessement.code.account.dto.AccountTransactionDto;
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
public class TransactionDaoTest {
    @Autowired
    TransactionDao transactionDao;

    @Test
    @Sql("/test.sql")
    public void testFindByCustomerId() {
        List<AccountTransactionDto> accountTransactionDtos = transactionDao.findTransByAccountId(1001L, 2001L);
        Assertions.assertThat(accountTransactionDtos.size()).isEqualTo(4);
    }

    @Test
    public void testFindByUnknownCustomerId() {
        List<AccountTransactionDto> accountTransactionDtos = transactionDao.findTransByAccountId(10044L, 2001L);
        Assertions.assertThat(accountTransactionDtos.size()).isEqualTo(0);
    }
}

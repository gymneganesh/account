package com.assessement.code.account.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TRANSACTION")
public class Transaction {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_id")
    @NotNull
    private Long accountId;

    @Column(name = "value_date")
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column
    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @Column
    @NotNull
    private BigDecimal amount;

    @Column
    @NotNull
    private String description;

}

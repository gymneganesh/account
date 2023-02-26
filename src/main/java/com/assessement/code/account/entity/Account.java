package com.assessement.code.account.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ACCOUNT")
public class Account {

    @Id
    @Column(name = "id")
    private Long accountNumber;

    @Column(name = "customer_id")
    @NotNull
    private Long customerId;

    @Column(name = "name")
    @NotNull
    private String accountName;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @Column
    @NotNull
    private String currency;

}

package com.assessement.code.account.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
public class Customer {

    @Id
    @Column
    private Long id;

    @Column
    @NotNull
    private String name;
}

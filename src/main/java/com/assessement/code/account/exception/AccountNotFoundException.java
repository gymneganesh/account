package com.assessement.code.account.exception;

public class AccountNotFoundException extends RuntimeException {

    public AccountNotFoundException(Long customerId) {
        super("Couldn't find any accounts for the user " + customerId);
    }
}

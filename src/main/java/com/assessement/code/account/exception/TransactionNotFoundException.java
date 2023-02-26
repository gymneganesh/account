package com.assessement.code.account.exception;

public class TransactionNotFoundException extends RuntimeException {
    public TransactionNotFoundException(Long accountId) {
        super("Couldn't find any transactions for the account " + accountId);
    }
}

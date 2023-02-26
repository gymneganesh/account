package com.assessement.code.account.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorMessage {
    private String errorMessage;
    private Integer errorCode;
}

package com.alfa_bank.testframework.enums;

import lombok.Getter;

@Getter
public enum AuthorisationFields {
    USERNAME("etUsername"),
    PASSWORD("etPassword");

    private final String fieldName;

    AuthorisationFields(String fieldName) {
        this.fieldName = fieldName;
    }
}

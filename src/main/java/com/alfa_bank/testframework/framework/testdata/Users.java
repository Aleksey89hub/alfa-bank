package com.alfa_bank.testframework.framework.testdata;

import com.alfa_bank.testframework.model.User;

import static com.alfa_bank.testframework.framework.config.ITestConfig.CONFIG;

public class Users {
    public static User createAdminUser() {
        return User.builder().
                userName(CONFIG.adminName())
                .password(CONFIG.adminPassword())
                .build();
    }

    public static User createUser() {
        return User.builder().
                userName(CONFIG.userName())
                .password(CONFIG.userPassword())
                .build();
    }
}

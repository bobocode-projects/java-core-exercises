package com.bobocode.function;

import com.bobocode.model.Account;

@FunctionalInterface
public interface AccountService {
    void processAccount(Account account);

    default void processWithNoAccount(){
        /* No operation */
    }
}

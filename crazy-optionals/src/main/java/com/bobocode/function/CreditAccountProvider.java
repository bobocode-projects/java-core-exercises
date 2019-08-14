package com.bobocode.function;

import com.bobocode.model.CreditAccount;

import java.util.Optional;

@FunctionalInterface
public interface CreditAccountProvider {
    Optional<CreditAccount> getAccount();
}

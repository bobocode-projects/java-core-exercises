package com.bobocode;

import com.bobocode.data.Accounts;
import com.bobocode.exception.AccountNotFoundException;
import com.bobocode.function.AccountProvider;
import com.bobocode.function.AccountService;
import com.bobocode.function.CreditAccountProvider;
import com.bobocode.model.Account;
import com.bobocode.model.CreditAccount;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

import static java.util.Comparator.comparing;

public class CrazyOptionals {

    /**
     * Creates an instance of {@link Optional<String>} using a text parameter
     *
     * @param text
     * @return optional object that holds text
     */
    public static Optional<String> optionalOfString(@Nullable String text) {
        return Optional.ofNullable(text); // Optional.ofNullable() will use Optional.empty() if text is null
    }

    /**
     * Adds a provided amount of money to the balance of an optional account.
     *
     * @param accountProvider
     * @param amount          money to deposit
     */
    public static void deposit(AccountProvider accountProvider, BigDecimal amount) {
        accountProvider.getAccount()
                .ifPresent(account -> account.setBalance(account.getBalance().add(amount))); // instead of using if operator
        // you can pass Consumer object that will be used in case Optional is not empty
        // this approach is called declarative and is usually more precise
    }

    /**
     * Creates an instance of {@link Optional<Account>} using an account parameter
     *
     * @param account
     * @return optional object that holds account
     */
    public static Optional<Account> optionalOfAccount(@Nonnull Account account) {
        return Optional.of(account); // Optional.of() will throw NullPointerException if account is null
    }

    /**
     * Returns the {@link Account} got from {@link AccountProvider}. If account provider does not provide an account,
     * returns a defaultAccount
     *
     * @param accountProvider
     * @param defaultAccount
     * @return account from provider or defaultAccount
     */
    public static Account getAccount(AccountProvider accountProvider, Account defaultAccount) {
        return accountProvider.getAccount()
                .orElse(defaultAccount); // Optional#orElse() can be used to provide default value is case Optional is empty
    }

    /**
     * Passes account to {@link AccountService#processAccount(Account)} when account is provided.
     * Otherwise calls {@link AccountService#processWithNoAccount()}
     *
     * @param accountProvider
     * @param accountService
     */
    public static void processAccount(AccountProvider accountProvider, AccountService accountService) {
        accountProvider.getAccount()
                .ifPresentOrElse(accountService::processAccount, accountService::processWithNoAccount);
        // one more declarative substitution of if-else operator.
        // Please note its parameters: Consumer and Runnable
    }

    /**
     * Returns account provided by {@link AccountProvider}. If no account is provided it generates one using {@link Accounts}
     * Please note that additional account should not be generated if {@link AccountProvider} returned one.
     *
     * @param accountProvider
     * @return provided or generated account
     */
    public static Account getOrGenerateAccount(AccountProvider accountProvider) {
        return accountProvider.getAccount()
                .orElseGet(Accounts::generateAccount); // functionally it works exactly the same as Optional#orElse()
        // however it is based on lazy initialization using Supplier interface, which means that default value
        // will not be computed (created) until Supplier#get() is called, which means it will be only computed
        // when Optional is empty. This method should be used in favor of Optional#orElse() when the creation of default
        // value requires additional resources
    }

    /**
     * Retrieves a {@link BigDecimal} balance using null-safe mappings.
     *
     * @param accountProvider
     * @return optional balance
     */
    public static Optional<BigDecimal> retrieveBalance(AccountProvider accountProvider) {
        return accountProvider.getAccount()
                .map(Account::getBalance); // a null-safe mapping that allows you to go from Optional object to its field
    }

    /**
     * Returns an {@link Account} provided by {@link AccountProvider}. If no account is provided,
     * throws {@link AccountNotFoundException} with a message "No Account provided!"
     *
     * @param accountProvider
     * @return provided account
     */
    public static Account getAccount(AccountProvider accountProvider) {
        return accountProvider.getAccount()
                .orElseThrow(() -> new AccountNotFoundException("No Account provided!")); // in case Optional is empty
        // it allows to throw a custom exception
    }

    /**
     * Retrieves a {@link BigDecimal} credit balance using null-safe mappings.
     *
     * @param accountProvider
     * @return optional credit balance
     */
    public static Optional<BigDecimal> retrieveCreditBalance(CreditAccountProvider accountProvider) {
        return accountProvider.getAccount()
                .flatMap(CreditAccount::getCreditBalance); // in case your getter already return Optional, you cannot use
        // Optional#map() because it will create Optional<Optional<Account>>. In this case Optional#flatMap() should be used
    }


    /**
     * Retrieves an {@link Account} with gmail email using {@link AccountProvider}. If no account is provided or it gets
     * not gmail then returns {@link Optional#empty()}
     *
     * @param accountProvider
     * @return optional gmail account
     */
    public static Optional<Account> retrieveAccountGmail(AccountProvider accountProvider) {
        return accountProvider.getAccount()
                .filter(account -> account.getEmail().split("@")[1].equals("gmail.com"));
        // in case you need to check if an Optional Account meets some criteria and return it or if it does not
        // then return Optional.empty() and do that in a null-safe manner
    }

    /**
     * Retrieves account using {@link AccountProvider} and fallbackProvider. In case main provider does not provide an
     * {@link Account} then account should ge retrieved from fallbackProvider. In case both provider returns no account
     * then {@link java.util.NoSuchElementException} should be thrown.
     *
     * @param accountProvider
     * @param fallbackProvider
     * @return account got from either accountProvider or fallbackProvider
     */
    public static Account getAccountWithFallback(AccountProvider accountProvider, AccountProvider fallbackProvider) {
        return accountProvider.getAccount()
                .or(fallbackProvider::getAccount) // allows to use another Optional in case main Optional is empty
                .orElseThrow(); // if both providers return Optional.empty() it throws NoSuchElementException
    }

    /**
     * Returns an {@link Accounts} with the highest balance. Throws {@link java.util.NoSuchElementException} if input
     * list is empty
     *
     * @param accounts
     * @return account with the highest balance
     */
    public static Account getAccountWithMaxBalance(List<Account> accounts) {
        return accounts.stream()
                .max(comparing(Account::getBalance)) // as you probably know Stream#min() and Stream#max() return Optional
                .orElseThrow();
    }

    /**
     * Returns the lowest balance values or empty if accounts list is empty
     *
     * @param accounts
     * @return the lowest balance values
     */
    public static OptionalDouble findMinBalanceValue(List<Account> accounts) {
        return accounts.stream()
                .map(Account::getBalance) // map all stream accounts to balances
                .mapToDouble(BigDecimal::doubleValue) // map all balances to primitive double values (returns DoubleStream)
                .min(); // Optional API provides special classes for primitives as well
    }

    /**
     * Finds an {@link Account} with max balance and processes it using {@link AccountService#processAccount(Account)}
     *
     * @param accounts
     * @param accountService
     */
    public static void processAccountWithMaxBalance(List<Account> accounts, AccountService accountService) {
        accounts.stream()
                .max(comparing(Account::getBalance)) // returns Optional account
                .ifPresent(accountService::processAccount); // declarative if statement and processing
        // the last method requires Consumer<Account> as argument, it is implements using method reference
    }

    /**
     * Calculates a sum of {@link CreditAccount#getCreditBalance()} of all accounts
     *
     * @param accounts
     * @return total credit balance
     */
    public static double calculateTotalCreditBalance(List<CreditAccount> accounts) {
        return accounts.stream()
                .map(CreditAccount::getCreditBalance) // transforms each element of stream into Optional<BigDecimal>
                .flatMap(Optional::stream) // uses special Optional#stream() to filter all elements that are empty
                .mapToDouble(BigDecimal::doubleValue) // transform BigDecimal into primitive double (returns DoubleStream)
                .sum(); // calculates a sum of primitive double
    }
}


package de.syed.dirtybankapi.services;

import de.syed.dirtybankapi.domain.Account;
import de.syed.dirtybankapi.domain.User;
import de.syed.dirtybankapi.repository.AccountRepository;
import de.syed.dirtybankapi.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    public AccountService(AccountRepository accountRepository, UserRepository userRepository){
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }

    // Create account Taking- accountNumber, username, and initial balance
    public Account createAccount(String accountNumber, String username, double initialBalance){
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("User not found: " + username)
        );

        Account account = new Account();
        account.setAccountNumber(accountNumber);
        account.setBalance(BigDecimal.valueOf(initialBalance));
        account.setUser(user);

        return accountRepository.save(account);
    }

    // Fetch an account by accountNumber
    public Account getAccountByNumber(String accountNumber){
        Optional<Account> accountOpt = accountRepository.findByAccountNumber(accountNumber);
        return accountOpt.orElse(null);
    }
}

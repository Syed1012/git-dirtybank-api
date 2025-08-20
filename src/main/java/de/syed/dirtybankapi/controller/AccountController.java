package de.syed.dirtybankapi.controller;

import de.syed.dirtybankapi.domain.Account;
import de.syed.dirtybankapi.services.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService){
        this.accountService = accountService;
    }

    // Create a new account
    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestParam String accountNumber,
                                                 @RequestParam String username,
                                                 @RequestParam double initialBalance){
        Account account = accountService.createAccount(accountNumber, username, initialBalance);
        return ResponseEntity.ok(account);
    }

    // Get an account by account number
    @GetMapping("/{accountNumber}")
    public ResponseEntity<Account> getAccount(@PathVariable String accountNumber){
        Account account = accountService.getAccountByNumber(accountNumber);
        if(account != null){
            return ResponseEntity.ok(account);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

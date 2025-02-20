package com.workintech.s18d4.controller;

import com.workintech.s18d4.dto.AccountResponse;
import com.workintech.s18d4.dto.CustomerResponse;
import com.workintech.s18d4.entity.Account;
import com.workintech.s18d4.entity.Customer;
import com.workintech.s18d4.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {
    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping()
    public List<Account> findAll(){
        return accountService.findAll();
    }

    @GetMapping("/{id}")
    public Account getAccountById(@PathVariable Long id) {
        return accountService.find(id);
    }

    @PostMapping("/{customerId}")
    public CustomerResponse saveAccount(@PathVariable Long customerId, @RequestBody Account account) {
        Account addedAccount = accountService.save(account);
        return new CustomerResponse(addedAccount.getId(), addedAccount.getAccountName(), addedAccount.getMoneyAmount());
    }

    @PutMapping("/{id}")
    public AccountResponse updateAccount(@PathVariable Long id, @RequestBody Account account){
        accountService.update(id, account);
        Account updatedAcc = accountService.find(id);
        return new AccountResponse(updatedAcc.getId(), updatedAcc.getAccountName(), updatedAcc.getMoneyAmount());
    }

    @DeleteMapping("/{id}")
    public AccountResponse deleteAccount(@PathVariable Long id){
        Account account = accountService.find(id);
        accountService.delete(id);
        return new AccountResponse(account.getId(), account.getAccountName(), account.getMoneyAmount());
    }
}

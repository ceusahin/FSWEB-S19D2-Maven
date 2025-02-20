package com.workintech.s18d4.service;

import com.workintech.s18d4.entity.Account;
import com.workintech.s18d4.repository.AccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService{
    private AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account find(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(
                        () -> new RuntimeException("Account with this ID does not exist.  ID: " + id));
    }

    @Override
    @Transactional
    public Account save(Account account) {
        return accountRepository.save(account);
    }

    @Override
    @Transactional
    public void update(Long id, Account account) {
        Account existingAccount = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Can not find account with this ID. ID: " + id));
        existingAccount.setAccountName(account.getAccountName());
        existingAccount.setMoneyAmount(account.getMoneyAmount());
        existingAccount.setCustomer(account.getCustomer());

        accountRepository.save(account);
    }

    @Override
    @Transactional
    public Account delete(Long id) {
        Optional<Account> deletedAccountOptional = accountRepository.findById(id);
        if (deletedAccountOptional.isPresent()){
            accountRepository.delete(deletedAccountOptional.get());
            return deletedAccountOptional.get();
        } else {
            return null;
        }
    }
}

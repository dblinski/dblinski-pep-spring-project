package com.example.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;

@Service
public class AccountService {
    
    private AccountRepository accountRepository;
    @Autowired
    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    public Account insertAccount(Account account){
        //check if username not blank, password >3 chars, account doesn't already exist
        //return 200 if success
        //return 409 if duplicate username
        //return 400 otherwise
        if(account.getUsername().isEmpty() 
                || account.getUsername() == null
                || account.getPassword().length() < 4 
                || account.getPassword() == null){
            return null; //400
        }
        Account doesAccountExist = accountRepository.findAccountByUsername(account.getUsername());
        if(doesAccountExist == null){
            return accountRepository.save(account);
        }
        else{
            return new Account(account.getUsername(), account.getPassword()); //409
        }
    }

    public Account getAccountById(Integer accountId){
        Optional<Account> optionalAccount = accountRepository.findById(accountId);
        if(optionalAccount.isPresent()){
            return optionalAccount.get();
        }
        else{
            return null;
        }
    }

    public Account getAccountByUsernameAndPassword(String username, String password){
        return accountRepository.findAccountByUsernameAndPassword(username, password);
    }
    /*
     * 1. add account
     * 2. get account
     * 3. get account by ID
     */
}

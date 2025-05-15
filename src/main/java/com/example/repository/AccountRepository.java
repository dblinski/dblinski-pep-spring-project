package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
    /*
     * public Account insertAccount(Account account);
     * public Account getAccount(String username, String password);
     * public Account getAccountById(int account_id);
     * public boolean doesUsernameExist(String username); //should have been Account
     */
    Account findAccountByUsername(String username);
    Account findAccountByUsernameAndPassword(String username, String password);
}

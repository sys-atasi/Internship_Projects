package com.example.Banking_App.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Banking_App.model.Account;
import com.example.Banking_App.repository.AccountRepository;


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;


@RequestMapping("/api/accounts")
@RestController
public class AccountController {
     @Autowired
    private AccountRepository accountRepository;


    @PostMapping
    public Account createAccount(@RequestBody Account account) {
        return accountRepository.save(account);
    }
    @GetMapping
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable Long id) {
        return accountRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
      // delete from tutorials where id=?
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {
        accountRepository.deleteById(id);
        return ResponseEntity.noContent().build();
      }
    @PutMapping("/{id}")
  public ResponseEntity<Account> updateAccount(@PathVariable("id") long id, @RequestBody Account account) {
    Optional<Account> updateAccount = accountRepository.findById(id);

    if (updateAccount.isPresent()) {
        Account account_ = updateAccount.get();
        account_.setName(account.getName());
        account_.setEmail(account.getEmail());
        account_.setBalance(account.getBalance());
      return new ResponseEntity<>(accountRepository.save(account), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
}


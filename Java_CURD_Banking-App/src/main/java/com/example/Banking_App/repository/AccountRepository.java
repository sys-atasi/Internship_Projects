package com.example.Banking_App.repository;

import java.util.List;

import com.example.Banking_App.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
// import com.example.crud_restapi.model.Tutorial;

public interface AccountRepository extends JpaRepository<Account, Long> {
  List<Account> findByEmail(String email);
  List<Account> findByNameContainingIgnoreCase(String title);
}
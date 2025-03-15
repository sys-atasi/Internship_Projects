package com.example.Banking_App.model;

import jakarta.persistence.Column;
// import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;


@Table(name = "Banking_Application")
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @NotNull
    @Column(name = "Name")
    private String name;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "Email")
    private String email;
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "Balance")
    private  double balance;
    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    //Empty constructor
    public Account() {
  
    }
  
    public Account(String name, String email,double balance) {
      this.name = name;
      this.email = email;
      this.balance = balance;
    }

    @Override
    public String toString() {
      return "Banking_App [id=" + id + ", name=" + name + ", email=" + email + ", balance=" + balance + "]";
    }
  

}

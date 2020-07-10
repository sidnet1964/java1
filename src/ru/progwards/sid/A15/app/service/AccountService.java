package ru.progwards.sid.A15.app.service;

import ru.progwards.sid.A15.app.model.Account;

public interface AccountService {

    public double balance(Account account);
    public void deposit(Account account, double amount);
    public void withdraw(Account account, double amount);
    public void transfer(Account from, Account to, double amount);


}
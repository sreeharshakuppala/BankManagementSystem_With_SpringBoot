


package Bankmanagementsystem.demo.service;

import java.util.*;

import Bankmanagementsystem.demo.models.Transactions;
import Bankmanagementsystem.demo.repository.AccountRepository;
import Bankmanagementsystem.demo.repository.TransactionsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import Bankmanagementsystem.demo.models.Account;

import java.util.Optional;

@Service
public class AccountService
    {


    private final AccountRepository accountRepository;
    private final TransactionsRepository transactionsRepository;

//    @Autowired
//    AccountRepository accountRepository2;
//
//

    public AccountService(AccountRepository accountRepository, TransactionsRepository transactionsRepository) {
        this.accountRepository = accountRepository;
        this.transactionsRepository = transactionsRepository;
    }


    public Account createAccount(Account account)
    {


        Account saved_account = accountRepository.save(account);

        return saved_account;
    }


    public List<Account> getAllAccounts()
    {
       List<Account> accounts = accountRepository.findAll();
        return accounts;
    }

    public Double getTotalBalance() {


//        Optional<Double> totalBalance = accountStorage.values().stream().map(account -> {
//            return account.getbalance();
//        }).reduce((total, current)->{
//            return total + current;
//        });
//        if(totalBalance.isEmpty()) {
//            return 0.0;
//        } else {
//            return totalBalance.get();
//        }

        double totalamount = 0;
        List<Account> accounts = accountRepository.findAll().stream().toList();
        int i = 0;
        while (i < accounts.size())
        {
//            Account account = accounts.get(i);
            totalamount += accounts.get(i).getBalance();
            i++;
        }
//        for(Account account : accounts) {
//            totalamount += account.getbalance();
//        }

        return totalamount;
    }


    public Account getAccount(Long accountNumber)
    {

        return accountRepository.findById(accountNumber).orElse(null);

    }

    @Transactional
    public Account depositAmount(Long accountNumber, double amount) {

        Optional<Account> optionalAccount = Optional.of(accountRepository.findById(accountNumber).orElseThrow(() -> new RuntimeException("Not FOund")));

        if (optionalAccount.isEmpty())
        {
            return null;
        }

        if (amount <= 0)
        {
            return null;
        }

        Account account = optionalAccount.get();

      Transactions transactions = new Transactions();
//        transactions.setId(GenerationType.AUTO.ordinal());
        transactions.setAccountNumber(accountNumber);
       transactions.setBalance(amount);
       transactions.setType("deposit");
        transactionsRepository.save(transactions);


        account.setBalance(account.getBalance() + amount);
        return accountRepository.save(account);

    }

    @Transactional
    public Account withdrawAmount(Long accountNumber, double amount) {
        Optional<Account> optionalAccount = accountRepository.findById(accountNumber);
//                .orElseThrow((() -> new RuntimeException("Not Found"));

        if (optionalAccount.isEmpty()) {
            return null;
        }
        Account account = optionalAccount.get();

        if (account.getBalance() < amount)
        {
            return null;
        }
        Transactions transactions = new Transactions();

        transactions.setAccountNumber(accountNumber);
//        transactions.setBalance(GenerationType.AUTO.ordinal());
        transactions.setBalance(amount);
        transactions.setType("withdraw");
        transactionsRepository.save(transactions);

        account.setBalance(account.getBalance() - amount);
        return accountRepository.save(account);
    }

    public List<Transactions> TransactionsList (Long accountNumber)
    {

          List<Transactions> optional = transactionsRepository.findByAccountNumber(accountNumber);




        return optional;
    }

    public boolean deleteAccount(Long accountNumber)
    {

        if (!accountRepository.existsById(accountNumber))
        {
            return false;
        }
        accountRepository.deleteById(accountNumber);
        return true;

    }

}











package Bankmanagementsystem.demo.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;


import Bankmanagementsystem.demo.models.Transactions;
import Bankmanagementsystem.demo.repository.AccountRepository;
import Bankmanagementsystem.demo.repository.TransactionsRepository;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import Bankmanagementsystem.demo.models.Account;
import Bankmanagementsystem.demo.service.AccountService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/account")

public class AccountController
{

    private final AccountService accountService;
    private final AccountRepository accountRepository;
    private final TransactionsRepository transactionsRepository;

    public AccountController(AccountService accountService, AccountRepository accountRepository, TransactionsRepository transactionsRepository)
    {

        this.accountService = accountService;
        this.accountRepository = accountRepository;
        this.transactionsRepository = transactionsRepository;
    }

    @PostMapping("/create")
    public ResponseEntity<Account> createAccount(@RequestBody Account account)
    {
        if(accountService.getAccount(account.getAccountNumber()) != null)
        {
            return ResponseEntity.status(404).body(null);
        }

        Account saved = accountService.createAccount(account);

        return ResponseEntity.status(HttpStatus.CREATED).body(saved);

    }


    @PostMapping("/deposit")
    public ResponseEntity<String> depositAmount(@RequestParam Long accountNumber, @RequestParam double amount)
    {

       if(amount <= 0)
        {
            return ResponseEntity.badRequest().body("Invalid amount");
        }

       Account updated = accountService.depositAmount(accountNumber, amount);

       if(updated == null)
       {
          return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account not found");
       }


        return ResponseEntity.ok("accountNumber:"+ accountNumber +"Amount Sucessfully credited" + amount);

    }
    @PostMapping("/withdraw")
    public ResponseEntity<String> withdrawAmount(@RequestParam Long accountNumber, @RequestParam double amount)
    {
        if (amount <= 0)
     {
         return ResponseEntity.badRequest().body("Invalid amount");
     }
        Account updated = accountService.withdrawAmount(accountNumber,amount);
     if (updated == null)
     {
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient balance or account not found");
     }

        return ResponseEntity.ok("accountNumber:"+ accountNumber + "amount Successfully debited:" + amount);
    }

   @DeleteMapping("/delete")
   public ResponseEntity<String> removeAccount(@RequestParam Long accountNumber)
   {
       boolean deleted = accountService.deleteAccount(accountNumber);
       if(!deleted)
       {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account not found");
       }
       return ResponseEntity.ok("Account deleted successfully");
   }


   @GetMapping("/balance")
   public ResponseEntity<Account> accountBalance(@RequestParam Long accountNumber)
   {

       Account account = accountService.getAccount(accountNumber);
       account.getBalance();

       if(account == null)
       {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
       }

       return ResponseEntity.ok(account);

   }


   @GetMapping("/transactions")

       public ResponseEntity<List<Transactions>> transactionHistory(@RequestParam Long accountNumber)
       {
           List<Transactions> transactions = accountService.TransactionsList(accountNumber);


           if(transactions == null)
            {
                return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

         return ResponseEntity.ok(transactions);
       }


   @GetMapping("/accountslists")
   public ResponseEntity<String> accountLists()
   {
       List<Account> account = accountService.getAllAccounts();
       return ResponseEntity.ok("Accounts CreatedList:" + account);
   }


   @GetMapping("/allaccountsbalance")
    public Double bankBalance()
   {

       return accountService.getTotalBalance();

   }


}






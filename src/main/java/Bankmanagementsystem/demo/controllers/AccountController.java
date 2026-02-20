


package Bankmanagementsystem.demo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Bankmanagementsystem.demo.models.Account;
import Bankmanagementsystem.demo.service.AccountService;





@RestController
@RequestMapping("/account")

public class AccountController
{

    private final AccountService accountService;

    public AccountController(AccountService accountService)
    {

        this.accountService = accountService;

    }

    @PostMapping("/create")
    public ResponseEntity<String> createAccount(@RequestBody Account account)
    {

        if (accountService.getAccount(account.getAccountNumber()) != null)
        {
            return ResponseEntity.badRequest().body("Account already exists" + account.getAccountNumber());
        }

        return ResponseEntity.ok(accountService.createAccount(account));

    }

    @PostMapping("/deposit")
    public ResponseEntity<String> depositAmount(@RequestParam Long accountNumber, @RequestParam double amount)
    {
        Account account =  accountService.getAccount(accountNumber);
        if(account == null)
        {
            return ResponseEntity.status(404).body(accountNumber + " " + ":Account not Found");
        }
        if(amount <= 0)
        {
            return ResponseEntity.badRequest().body("deposit Amount should be > 0");
        }
        return ResponseEntity.ok("successfully credited, accountNumber:" + accountService.depositAmount(accountNumber, amount));

    }
    @PostMapping("/withdraw")
    public ResponseEntity<String> withdrawAmount(@RequestParam Long accountNumber, @RequestParam double amount)
    {
        Account account = accountService.getAccount(accountNumber);
     if(account == null)
     {
         return ResponseEntity.status(404).body(accountNumber + " " + ":Account not Found");
     }
     if(amount > account.getbalance())
     {
         return ResponseEntity.badRequest().body("Insufficient withdraw Amount");
     }

        return ResponseEntity.ok("successfully debited, accountNumber" + ":" + accountService.withdrawAmount(accountNumber, amount));

    }
   @DeleteMapping("/delete")
   public ResponseEntity<String> removeAccount(@RequestParam Long accountNumber)
   {
       Account account = accountService.getAccount(accountNumber);
       if(account == null)
       {
           return ResponseEntity.status(404).body(accountNumber + " " + ":Account not Found");
       }
       return ResponseEntity.ok(accountService.deleteAccount(accountNumber) +":" + "Account Successfully deleted");
   }

}






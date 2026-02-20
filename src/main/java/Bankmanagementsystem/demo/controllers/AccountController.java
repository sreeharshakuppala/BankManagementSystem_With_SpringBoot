


package Bankmanagementsystem.demo.controllers;

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
    public String createAccount(@RequestBody Account account) 
    {

        return accountService.createAccount(account);

    }
    
    @PostMapping("/deposit")
    public String depositAmount(@RequestParam Long accountNumber, @RequestParam double amount)
    {

        return accountService.depositAmount(accountNumber, amount);

    }
    @PostMapping("/withdraw")
    public String withdrawAmount(@RequestParam Long accountNumber, @RequestParam double amount)
    {

        return accountService.withdrawAmount(accountNumber, amount);

    }
   @DeleteMapping("/delete")
   public String removeAccount(@RequestParam Long accountNumber)
   {
           
       return accountService.deleteAccount(accountNumber);
   }
   
    
   

}






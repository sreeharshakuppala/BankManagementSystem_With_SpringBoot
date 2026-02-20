


package Bankmanagementsystem.demo.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import Bankmanagementsystem.demo.models.Account;

@Service
public class AccountService 
{

    private Map<Long, Account> accountStorage = new HashMap<>();

    public String createAccount(Account account) 
    {
        accountStorage.put(account.getAccountNumber(), account);
        return "Account created successfullly!";

    }

    public Account getAccount(Long accountNumber) 
    {
        return accountStorage.get(accountNumber);

    }
    
     
    public String depositAmount(Long accountNumber, double amount) 
    {

        Account account = accountStorage.get(accountNumber);

        if (account == null) {
            return "Account Not Found";
        }

        if (amount <= 0) {
            return "Invalid deposit amount";
        }
        double newBalance = account.getbalance() + amount;
        account.setBalance(newBalance);

        return "Deposited " + amount + "Successfully. New Balance: " + newBalance;

    }

    public String withdrawAmount(Long accountNumber,double amount)
    {
        Account account = accountStorage.get(accountNumber);
        
        if(account == null)
        {
            return "Account Not Found";
        }
        if(amount <= 0)
        {
            return "Invalid withdraw amount";
        }
        double currentBalance = account.getbalance() - amount;
        account.setBalance(currentBalance);
        
        return "withdraw " + amount + "Successfully. New Balance: " + currentBalance;
    }

    public  String deleteAccount(Long accountNumber)
    {

        Account account = accountStorage.remove(accountNumber);
        return "Account Successfully deleted";

    }

   

   

   


}








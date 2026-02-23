


package Bankmanagementsystem.demo.service;

import java.util.*;

import org.springframework.stereotype.Service;

import Bankmanagementsystem.demo.models.Account;

@Service
public class AccountService {

    private Map<Long, Account> accountStorage = new HashMap<>();
//   private List<Long> totalbalance = new ArrayList<Long>();

    public String createAccount(Account account) {
        accountStorage.put(account.getAccountNumber(), account);
//        totalbalance.add(account.getAccountNumber());
        return "Account created successfullly!";

    }

    public List<Account> getAllAccounts() {
        return accountStorage.values().stream().toList();
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
        List<Account> accounts = (accountStorage.values().stream().toList());
        int i = 0;
        while (i < accounts.size()) {
            Account account = accounts.get(i);
            totalamount += account.getbalance();
            i++;
        }
//        for(Account account : accounts) {
//            totalamount += account.getbalance();
//        }

        return totalamount;
    }


    public Account getAccount(Long accountNumber)
    {
        return accountStorage.get(accountNumber);

    }

    public String depositAmount(Long accountNumber, double amount) {

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

    public String withdrawAmount(Long accountNumber, double amount) {
        Account account = accountStorage.get(accountNumber);

        if (account == null) {
            return "Account Not Found";
        }
        if (amount <= 0) {
            return "Invalid withdraw amount";
        }
        double currentBalance = account.getbalance() - amount;
        account.setBalance(currentBalance);

        return "withdraw " + amount + "Successfully. New Balance: " + currentBalance;
    }


    public String deleteAccount(Long accountNumber)
    {

        Account account = accountStorage.remove(accountNumber);
        return "Account Successfully deleted";

    }

}








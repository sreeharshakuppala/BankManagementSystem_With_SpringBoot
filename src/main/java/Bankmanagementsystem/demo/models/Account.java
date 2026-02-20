

package Bankmanagementsystem.demo.models;

public class Account
{
    private String name;
    private String mobileNumber;
    private long accountNumber;
    private int age;
    private double balance;

   public Account()
   {

   }
    public Account(String name,String mobileNumber, long accountNumber, int age, double balance)
    {
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.accountNumber = accountNumber;
        this.age = age;
        this.balance = balance;

    }

    public String getName()
    {
        return name;
    }

    public String getmobileNumber()
    {
        return mobileNumber;
    }

    public long getAccountNumber()
    {
        return accountNumber;
    }

    public int getage()
    {
        return age;
    }
    
    public double getbalance()
    {
        return balance;
    }

    public void setBalance(double balance)
    {
        this.balance = balance;
    }
}
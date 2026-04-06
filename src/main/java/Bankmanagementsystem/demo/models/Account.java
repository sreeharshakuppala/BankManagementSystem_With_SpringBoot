

package Bankmanagementsystem.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "accounts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account
{
//    @Id
//    @Setter
//    @Getter
//    private Long id;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private String mobileNumber;
    @Id
    @Getter
    @Setter
    private long accountNumber;
    @Getter
    @Setter
    private int age;
    @Setter
    @Getter
    private double balance;


//    public Account(Long id,String name,String mobileNumber, long accountNumber, int age, double balance)
//    {
//        this.id = id;
//        this.name = name;
//        this.mobileNumber = mobileNumber;
//        this.accountNumber = accountNumber;
//        this.age = age;
//        this.balance = balance;
//
//    }

}
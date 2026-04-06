


package Bankmanagementsystem.demo.models;






import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.boot.registry.selector.spi.StrategyCreator;
import org.springframework.boot.autoconfigure.web.WebProperties;


@Entity
@Table(name = "transactions")
public class Transactions
{

@Id
@Setter
@Getter
@GeneratedValue(strategy = GenerationType.AUTO)
private long id;
    @Setter
 @Getter
 private long accountNumber;
    @Getter
    @Setter
    private double balance;
    @Getter
    @Setter
    private String type;

  public Transactions()
  {

  }
  public Transactions(long accountNumber,double balance,String type)
  {
      this.accountNumber = accountNumber;
      this.balance = balance;
      this.type = type;

  }


}








package Bankmanagementsystem.demo.repository;

import Bankmanagementsystem.demo.models.Transactions;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface TransactionsRepository extends JpaRepository<Transactions, Long>
{



         @Query(value = "SELECT * FROM transactions WHERE account_number = :accountNumber",nativeQuery = true)
         List<Transactions> findByAccountNumber(@Param("accountNumber") Long accountNumber);




}

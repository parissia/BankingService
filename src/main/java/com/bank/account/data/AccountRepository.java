package com.bank.account.data;

import com.bank.account.model.Account;
import com.bank.account.model.Transaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccountRepository extends CrudRepository<Account, Long> {

    @Query("select SUM(availableBalance) from Account a where a.customerId = ?#{#customerId}")
    Long findBalance(@Param("customerId") Long customerId);

    @Query("select accountId from Account a where a.customerId = ?#{#customerId}")
    List<Long> findAccounts(@Param("customerId") Long customerId);

}

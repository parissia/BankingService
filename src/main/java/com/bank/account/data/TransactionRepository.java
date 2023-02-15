package com.bank.account.data;

import com.bank.account.model.Transaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {

    @Query("select t from Transaction t where t.accountId = ?#{#accountId}")
    List<Transaction> findByAccountId(@Param("accountId") Long accountId);
}

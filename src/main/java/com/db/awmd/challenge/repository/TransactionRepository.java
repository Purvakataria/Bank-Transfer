package com.db.awmd.challenge.repository;

import java.util.List;
import com.db.awmd.challenge.domain.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository {
    List<Transaction> findByAccountNumberEquals(String accountNumber);

	Transaction save(Transaction transaction);

}
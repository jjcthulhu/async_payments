package com.asyncpayments.repository;

import com.asyncpayments.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByStatus(String status); // Busca transações por status
    List<Transaction> findAll();
}

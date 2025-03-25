package com.asyncpayments.service;

import com.asyncpayments.model.Transaction;
import com.asyncpayments.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction createTransaction(Double amount, String paymentMethod, String connectionType) {
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setStatus("PENDENTE");
        transaction.setPaymentMethod(paymentMethod);
        transaction.setConnectionType(connectionType);
        transaction.setCreatedAt(LocalDateTime.now());
        return transactionRepository.save(transaction);
    }

    public List<Transaction> getPendingTransactions() {
        return transactionRepository.findByStatus("PENDENTE");
    }

    public List<Transaction> getAllTransactions() {
	return transactionRepository.findAll();
    }

    public void processTransaction(Long id, String status) {
        Transaction transaction = transactionRepository.findById(id).orElseThrow();
        transaction.setStatus(status);
        transaction.setProcessedAt(LocalDateTime.now());
        transactionRepository.save(transaction);
    }
}

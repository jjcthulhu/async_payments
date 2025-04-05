package com.asyncpayments.service;

import com.asyncpayments.model.Saldos;
import com.asyncpayments.model.Transaction;
import com.asyncpayments.repository.SaldosRepository;
import com.asyncpayments.repository.TransactionRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
   
    @Service
    @Transactional
    public class TransactionService {

        @Autowired
        private TransactionRepository transactionRepository;
        
        @Autowired
        private SaldosRepository saldosRepository;

        // Método para obter todas as transações
        public List<Transaction> getAllTransactions() {
            return transactionRepository.findAll();
        }

        public List<Transaction> getPendingTransactions() {
            try {
                return transactionRepository.findByStatus("PENDENTE");
            } catch (Exception e) {
                throw new RuntimeException("Falha ao recuperar transações pendentes");
            }
        }

        // Método para obter transação por ID
        public Transaction getTransactionById(Long id) {
            return transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transação não encontrada"));
        }

        @Transactional
        public Transaction createTransaction(String pagadorId, String recebedorId,
                                          Double amount, String paymentMethod,
                                          String connectionType) {
            
            // Verifica ou cria saldo do pagador
            Saldos saldoPagador = saldosRepository.findByUsuarioId(pagadorId);
            if (saldoPagador == null) {
                saldoPagador = new Saldos();
                saldoPagador.setUsuarioId(pagadorId);
                saldoPagador.setSaldo(1000.00); // Valor inicial padrão
                saldosRepository.save(saldoPagador);
            }

            // Verifica ou cria saldo do recebedor
            Saldos saldoRecebedor = saldosRepository.findByUsuarioId(recebedorId);
            if (saldoRecebedor == null) {
                saldoRecebedor = new Saldos();
                saldoRecebedor.setUsuarioId(recebedorId);
                saldoRecebedor.setSaldo(0.00);
                saldosRepository.save(saldoRecebedor);
            }

            // Valida saldo
            if (saldoPagador.getSaldo() < amount) {
                throw new RuntimeException("Saldo insuficiente para o pagador " + pagadorId + 
                                       ". Saldo atual: " + saldoPagador.getSaldo());
            }

            // Cria a transação
            Transaction transaction = new Transaction();
            transaction.setPagadorId(pagadorId);
            transaction.setRecebedorId(recebedorId);
            transaction.setAmount(amount);
            transaction.setStatus("PENDENTE");
            transaction.setPaymentMethod(paymentMethod);
            transaction.setConnectionType(connectionType);
            transaction.setCreatedAt(LocalDateTime.now());
            
            return transactionRepository.save(transaction);
        }

    public void processTransaction(Long transactionId, String status) {
        Transaction transaction = transactionRepository.findById(transactionId)
            .orElseThrow(() -> new RuntimeException("Transação não encontrada"));

        if ("APROVADA".equals(status)) {
            // Atualiza saldos apenas se for aprovar
            Saldos pagador = saldosRepository.findByUsuarioId(transaction.getPagadorId());
            Saldos recebedor = saldosRepository.findByUsuarioId(transaction.getRecebedorId());
            
            pagador.setSaldo(pagador.getSaldo() - transaction.getAmount());
            recebedor.setSaldo(recebedor.getSaldo() + transaction.getAmount());
            
            saldosRepository.save(pagador);
            saldosRepository.save(recebedor);
        }
        
        transaction.setStatus(status);
        transaction.setProcessedAt(LocalDateTime.now());
        transactionRepository.save(transaction);
    }
}

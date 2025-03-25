package com.asyncpayments.controller;

import com.asyncpayments.model.Transaction;
import com.asyncpayments.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/intermediario")
public class IntermediarioController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/transacoes/pendentes")
    public List<Transaction> listarTransacoesPendentes() {
        return transactionService.getPendingTransactions();
    }

    @PostMapping("/transacoes/processar/{id}")
    public String processarTransacao(@PathVariable Long id, @RequestParam String status) {
        transactionService.processTransaction(id, status);
        return "Transação " + id + " processada com status: " + status;
    }

    @GetMapping("/logs")
    public List<Transaction> listarLogs() {
        return transactionService.getAllTransactions();
    }
}

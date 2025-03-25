package com.asyncpayments.controller;

import com.asyncpayments.model.Transaction;
import com.asyncpayments.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pagador")
public class PagadorController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/pagar")
    public String realizarPagamento(@RequestParam Double amount, @RequestParam String paymentMethod, @RequestParam String connectionType) {
        Transaction transaction = transactionService.createTransaction(amount, paymentMethod, connectionType);
        return "Pagamento de R$ " + amount + " iniciado. ID da transação: " + transaction.getId();
    }
}

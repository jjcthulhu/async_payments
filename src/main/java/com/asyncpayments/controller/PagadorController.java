package com.asyncpayments.controller;

import com.asyncpayments.model.Transaction;
import com.asyncpayments.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/pagador")
public class PagadorController {

    @Autowired
    private TransactionService transactionService;

    // Método para exibir o formulário (GET)
    @GetMapping("/pagar")
    public String mostrarFormularioPagamento(Model model) {
        model.addAttribute("transaction", new Transaction());
        return "pagador/pagar"; // Nome do template Thymeleaf
    }

    // Método para processar o formulário (POST)
    @PostMapping("/pagar")
    public String processarPagamento(@ModelAttribute Transaction transaction, Model model) {
        Transaction novaTransacao = transactionService.createTransaction(
            transaction.getAmount(),
            transaction.getPaymentMethod(),
            transaction.getConnectionType()
        );
        model.addAttribute("mensagemSucesso", 
            "Pagamento de R$ " + transaction.getAmount() + " criado com ID: " + novaTransacao.getId());
        return "pagador/pagar"; // Retorna para a mesma página com mensagem
    }
}

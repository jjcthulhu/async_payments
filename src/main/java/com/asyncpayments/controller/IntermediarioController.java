package com.asyncpayments.controller;

import com.asyncpayments.model.Transaction;
import com.asyncpayments.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/intermediario")
public class IntermediarioController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/transacoes/pendentes")
    public String listarTransacoesPendentes(Model model) {
        model.addAttribute("transacoes", transactionService.getPendingTransactions());
        return "intermediario/pendentes";
    }

    @GetMapping("/transacoes/processar/{id}")
    public String showProcessarForm(@PathVariable Long id, Model model) {
        model.addAttribute("transacao", transactionService.getTransactionById(id));
        return "intermediario/processar";
    }

    @PostMapping("/transacoes/processar/{id}")
    public String processarTransacao(@PathVariable Long id, 
                                   @RequestParam String status,
                                   RedirectAttributes redirectAttributes) {
        transactionService.processTransaction(id, status);
        redirectAttributes.addFlashAttribute("message", 
            "Transação " + id + " processada como: " + status);
        return "redirect:/intermediario/transacoes/pendentes";
    }

    @GetMapping("/logs")
    public String listarLogs(Model model) {
        model.addAttribute("transacoes", transactionService.getAllTransactions());
        return "intermediario/logs";
    }
}

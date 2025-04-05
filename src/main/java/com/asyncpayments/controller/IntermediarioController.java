package com.asyncpayments.controller;

import com.asyncpayments.model.Saldos;
import com.asyncpayments.model.Transaction;
import com.asyncpayments.repository.SaldosRepository;
import com.asyncpayments.service.TransactionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/intermediario")
public class IntermediarioController {

    @Autowired
    private TransactionService transactionService;
    
    @Autowired
    private SaldosRepository saldosRepository; // Adicione esta linha

    @GetMapping("/transacoes/pendentes")
    public String listarTransacoesPendentes(Model model) {
        List<Transaction> transacoes = transactionService.getPendingTransactions();
        
        // Carrega os saldos para cada transação
        Map<String, Double> saldos = new HashMap<>();
        for (Transaction tx : transacoes) {
            Saldos saldoPagador = saldosRepository.findByUsuarioId(tx.getPagadorId());
            Saldos saldoRecebedor = saldosRepository.findByUsuarioId(tx.getRecebedorId());
            
            saldos.put(tx.getPagadorId(), saldoPagador != null ? saldoPagador.getSaldo() : 0.0);
            saldos.put(tx.getRecebedorId(), saldoRecebedor != null ? saldoRecebedor.getSaldo() : 0.0);
        }
        
        model.addAttribute("transacoes", transacoes);
        model.addAttribute("saldos", saldos);
        return "intermediario/pendentes";
    }
    
    @GetMapping("/transacoes/processar/{id}")
    public String mostrarFormProcessamento(@PathVariable Long id, Model model) {
        model.addAttribute("transacao", transactionService.getTransactionById(id));
        return "intermediario/processar";
    }

    @GetMapping("/logs")
    public String listarLogsTransacoes(Model model) {
        model.addAttribute("transacoes", transactionService.getAllTransactions());
        return "intermediario/logs";
    }

    @PostMapping("/transacoes/processar/{id}")
    public String processarTransacao(@PathVariable Long id,
                                   @RequestParam String status,
                                   RedirectAttributes redirectAttributes) {
        transactionService.processTransaction(id, status);
        redirectAttributes.addFlashAttribute("sucesso", 
            "Transação " + id + " processada como: " + status);
        return "redirect:/intermediario/transacoes/pendentes";
    }
}


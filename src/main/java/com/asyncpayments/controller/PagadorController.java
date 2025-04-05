package com.asyncpayments.controller;

import com.asyncpayments.dto.TransactionForm;
import com.asyncpayments.model.Saldos;
import com.asyncpayments.model.Transaction;
import com.asyncpayments.repository.SaldosRepository;
import com.asyncpayments.service.TransactionService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/pagador")
public class PagadorController {

    @Autowired
    private TransactionService transactionService;
    
    @Autowired
    private SaldosRepository saldosRepository;

    private String getUsuarioLogado() {
        // Implementação temporária - retorne um ID fixo para testes
        return "pagador_123";
    }

    @GetMapping("/pagar")
    public String mostrarFormulario(Model model) {
        model.addAttribute("transactionForm", new TransactionForm());
        return "pagador/pagar";
    }

    @PostMapping("/pagar")
    public String processarPagamento(
            @Valid @ModelAttribute TransactionForm form,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes) {
        
        if (result.hasErrors()) {
            return "pagador/pagar";
        }

        try {
            String pagadorId = getUsuarioLogado();
            Transaction transaction = transactionService.createTransaction(
                pagadorId,
                form.getRecebedorId(),
                form.getAmount(),
                form.getPaymentMethod(),
                form.getConnectionType()
            );
            
            redirectAttributes.addFlashAttribute("sucesso", 
                "Pagamento de R$ " + form.getAmount() + " criado com sucesso! ID: " + transaction.getId());
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("erro", e.getMessage());
        }
        
        return "redirect:/pagador/pagar";
    }
}
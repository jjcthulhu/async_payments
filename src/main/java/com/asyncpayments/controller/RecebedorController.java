package com.asyncpayments.controller;

import com.asyncpayments.model.Saldos;
import com.asyncpayments.repository.SaldosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/recebedor")
public class RecebedorController {

    @Autowired
    private SaldosRepository saldosRepository;

    private String getUsuarioLogado() {
        // Implementação temporária
        return "recebedor_456";
    }

    @GetMapping("/saldo")
    public String consultarSaldo(Model model) {
        Saldos saldo = saldosRepository.findByUsuarioId(getUsuarioLogado());
        model.addAttribute("saldo", saldo != null ? saldo.getSaldo() : 0.0);
        return "recebedor/saldo";
    }
}
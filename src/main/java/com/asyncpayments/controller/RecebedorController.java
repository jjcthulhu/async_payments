package com.asyncpayments.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/recebedor")
public class RecebedorController {

    @GetMapping("/saldo")
    public String consultarSaldo(Model model) {
        model.addAttribute("saldo", "500,00");
        return "recebedor/saldo";
    }

    @GetMapping("/receber")
    public String receberPagamento(Model model) {
        model.addAttribute("mensagem", "Pagamento recebido com sucesso!");
        return "recebedor/receber";
    }
}

package com.asyncpayments.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recebedor")
public class RecebedorController {

    @GetMapping("/saldo")
    public String consultarSaldo() {
        return "Saldo do Recebedor: R$ 500,00";
    }

    @GetMapping("/receber")
    public String receberPagamento() {
        return "Pagamento recebido com sucesso!";
    }
}

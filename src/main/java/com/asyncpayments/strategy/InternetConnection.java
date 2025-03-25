package com.asyncpayments.strategy;

import org.springframework.stereotype.Component;

@Component
public class InternetConnection implements ConnectionStrategy {
    @Override
    public String connect() {
        return "Conexão via Internet estabelecida.";
    }
}

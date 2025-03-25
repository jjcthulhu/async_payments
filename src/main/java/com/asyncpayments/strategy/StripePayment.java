package com.asyncpayments.strategy;

import org.springframework.stereotype.Component;

@Component
public class StripePayment implements PaymentStrategy {
    @Override
    public String processPayment(Double amount) {
        return "Pagamento de R$ " + amount + " processado via Stripe.";
    }
}

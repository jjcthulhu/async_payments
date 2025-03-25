package com.asyncpayments.strategy;

public interface PaymentStrategy {
    String processPayment(Double amount);
}

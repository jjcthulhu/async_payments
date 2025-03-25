package com.asyncpayments.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;
    private String status; // PENDENTE, PROCESSADA, FALHA
    private String paymentMethod; // STRIPE, PAGARME, MERCADOPAGO, etc.
    private String connectionType; // INTERNET, SMS, BLUETOOTH, etc.
    private LocalDateTime createdAt;
    private LocalDateTime processedAt;
}

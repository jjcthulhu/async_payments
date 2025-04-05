package com.asyncpayments.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Double amount;
    private String status;
    private String paymentMethod;
    private String connectionType;
    private LocalDateTime createdAt;
    private LocalDateTime processedAt;
    
    @Column(nullable = false)
    private String pagadorId;
    
    @Column(nullable = false)
    private String recebedorId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getConnectionType() {
        return connectionType;
    }

    public void setConnectionType(String connectionType) {
        this.connectionType = connectionType;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getProcessedAt() {
        return processedAt;
    }

    public void setProcessedAt(LocalDateTime processedAt) {
        this.processedAt = processedAt;
    }

    public String getPagadorId() {
        return pagadorId;
    }

    public void setPagadorId(String pagadorId) {
        this.pagadorId = pagadorId;
    }

    public String getRecebedorId() {
        return recebedorId;
    }

    public void setRecebedorId(String recebedorId) {
        this.recebedorId = recebedorId;
    }
}
package com.asyncpayments.dto;

import jakarta.validation.constraints.*;

public class TransactionForm {
    @NotNull @DecimalMin("0.01")
    private Double amount;
    
    @NotBlank
    private String paymentMethod;
    
    @NotBlank
    private String connectionType;
    
    @NotBlank
    private String recebedorId;

    // Getters e Setters COMPLETOS
    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
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

    public String getRecebedorId() {
        return recebedorId;
    }

    public void setRecebedorId(String recebedorId) {
        this.recebedorId = recebedorId;
    }
}
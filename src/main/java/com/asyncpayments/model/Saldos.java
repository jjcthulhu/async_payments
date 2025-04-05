package com.asyncpayments.model;

import jakarta.persistence.*;

@Entity
@Table(name = "SALDOS") // Força o nome da tabela em maiúsculas
public class Saldos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "USUARIO_ID", nullable = false, unique = true)
    private String usuarioId;
    
    @Column(name = "SALDO", nullable = false)
    private Double saldo = 0.0;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }
}
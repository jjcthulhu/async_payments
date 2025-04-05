package com.asyncpayments.repository;

import com.asyncpayments.model.Saldos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaldosRepository extends JpaRepository<Saldos, Long> {
    Saldos findByUsuarioId(String usuarioId);
}
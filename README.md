# 💳 Async Payments System

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-F2F4F9?style=for-the-badge&logo=spring-boot)
![MySQL](https://img.shields.io/badge/mysql-%2300f.svg?style=for-the-badge&logo=mysql&logoColor=white)

> Sistema de processamento de pagamentos assíncronos com múltiplos gateways e estratégias de conexão

## 📌 Índice
- [Funcionalidades](#-funcionalidades)
- [Tecnologias](#-tecnologias)
- [Instalação](#-instalação)
- [Configuração](#%EF%B8%8F-configuração)
- [Uso](#-uso)
- [API](#-api)
- [Contribuição](#-contribuição)
- [Licença](#-licença)

## ✨ Funcionalidades

- Processamento assíncrono de transações
- Suporte a múltiplos gateways:
  - Stripe
  - Pagar.me
  - Mercado Pago
  - Square
- Diversos tipos de conexão:
  - Internet
  - SMS
  - Bluetooth
  - NFC
- Dashboard administrativo
- API RESTful documentada

## 🛠 Tecnologias

**Backend:**
- Java 17
- Spring Boot 3.1
- Spring Security
- JPA/Hibernate

**Frontend:**
- Thymeleaf
- Bootstrap 5
- Chart.js

**Banco de Dados:**
- MySQL 8.0
- Flyway (migrações)

**Infra:**
- Docker
- Docker Compose

## 🚀 Instalação

1. Clone o repositório:
```bash
git clone https://github.com/seu-usuario/async-payments.git
cd async-payments

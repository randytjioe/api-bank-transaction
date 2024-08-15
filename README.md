
# Nasabah Management System

## Overview
Nasabah Management System adalah aplikasi backend yang mengelola data nasabah, rekening, dan transaksi bank. Proyek ini dibangun menggunakan Java dan JDBC, dengan dukungan untuk fitur pagination pada transaksi.

## Features
- **Manajemen Nasabah**: CRUD untuk data nasabah.
- **Manajemen Rekening**: CRUD untuk rekening terkait nasabah.
- **Manajemen Transaksi**: Menyimpan, mengupdate, dan mengambil data transaksi.
- **Pagination**: Mendukung pagination untuk mengambil data transaksi.

## Technologies Used
- **Java 17**
- **Spring Boot**
- **JDBC**
- **Postgresql (Database)**
- **Maven (Build Tool)**

## Getting Started

### Prerequisites
- **Java 17**
- **Postgresql** - Ensure MySQL is installed and running.
- **Maven** - For build and dependency management.

### Installation

1. **Clone the Repository**
   ```
   git clone git@github.com:randytjioe/api-bank-transaction.git
   cd api-bank-transaction
    ```
2. Setup Database
   Buat database baru di Postgresql:
    ```
    CREATE DATABASE apiBankTransaction;
    ```
Update konfigurasi database di src/main/resources/application.properties:
```
spring.datasource.username=postgres
spring.datasource.password=password
spring.datasource.url=jdbc:postgresql://localhost:5432/apiBankTransaction
```

3. API Endpoints
- Authentication
    - POST /api/auth/register : Create a new nasabah.
    - POST /api/auth/login : Authenticate user and retrieve JWT.

- Nasabah Endpoints

    - GET /api/nasabah/{id} : Get nasabah by ID.
    - PUT /api/nasabah/{id} : Update nasabah information.

- Rekening Endpoints
    - GET /api/rekening : Get all rekening.
    - GET /api/rekening/{id} : Get rekening by ID.
    - POST /api/rekening : Create a new rekening.
    - PUT /api/rekening/{id} : Update rekening.
    - DELETE /api/rekening/{id} : Delete rekening.

- Transaksi Endpoints
    - GET /api/transaksi : Get all transactions (supports pagination).
    - GET /api/transaksi/{id} : Get transaction by ID.
    - POST /api/transaksi : Create a new transaction.
    - PUT /api/transaksi/{id} : Update transaction.
    - DELETE /api/transaksi/{id} : Delete transaction.



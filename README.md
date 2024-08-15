
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

## Base URL

The base URL for all API requests is:

```
{{URL}} - default is http://localhost:8080
```

## Authentication

This API uses Bearer token authentication. You need to include the token in the `Authorization` header as follows:

```
Authorization: Bearer {{token}}
```

## API Endpoints

### Nasabah (Customer)

1. **Register a New Nasabah**
    - **Method:** POST
    - **Endpoint:** `/api/auth/register`
    - **Body (JSON):**
      ```json
      {
        "nama": "Randy",
        "email": "randy2@mail.com",
        "password": "password",
        "alamat": "Padang",
        "tanggalLahir": "1995-03-01",
        "nomorTelepon": "087895238280"
      }
      ```

2. **Login**
    - **Method:** POST
    - **Endpoint:** `/api/auth/login`
    - **Body (JSON):**
      ```json
      {
        "email": "randy2@mail.com",
        "password": "password"
      }
      ```

3. **Update Profile**
    - **Method:** PUT
    - **Endpoint:** `/api/nasabah/{id}`
    - **Body (JSON):**
      ```json
      {
        "nama": "Rendy",
        "alamat": "Surabaya",
        "tanggalLahir": "1995-04-01",
        "nomorTelepon": "08789523800"
      }
      ```

### Rekening (Account)

1. **Create Rekening**
    - **Method:** POST
    - **Endpoint:** `/api/rekening`
    - **Body (JSON):**
      ```json
      {
        "nomorRekening": "12345678",
        "tipeRekening": "DEPOSITO",
        "saldo": 50000000
      }
      ```

2. **Get Rekening by Nasabah**
    - **Method:** GET
    - **Endpoint:** `/api/rekening/nasabah/{nasabahId}`

3. **Get Rekening by ID**
    - **Method:** GET
    - **Endpoint:** `/api/rekening/{rekeningId}`

4. **Delete Rekening**
    - **Method:** DELETE
    - **Endpoint:** `/api/rekening/{rekeningId}`

### Transaction

1. **Create Transaction**
    - **Method:** POST
    - **Endpoint:** `/api/transaksi`
    - **Body (JSON):**
      ```json
      {
        "jenisTransaksi": "TRANSFER",
        "jumlah": "800000",
        "rekeningId": "3"
      }
      ```

2. **Get Transaction by ID**
    - **Method:** GET
    - **Endpoint:** `/api/transaksi/{transaksiId}`

3. **Get Transactions by Rekening**
    - **Method:** GET
    - **Endpoint:** `/api/transaksi/rekening/{rekeningId}`
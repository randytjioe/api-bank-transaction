package com.bank.transaction.utils.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

public class AuthDto {
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RegisterRequest {
        @NotBlank(message = "Nama tidak boleh kosong")
        private String nama;
        @NotBlank(message = "Email tidak boleh kosong")
        private String email;
        @NotBlank(message = "Password tidak boleh kosong")
        private String password;
        @NotBlank(message = "Alamat tidak boleh kosong")
        private String alamat;
        @NotBlank(message = "Tanggal Lahir tidak boleh kosong")
        private String tanggalLahir;
        @NotBlank(message = "Nomor Telepon tidak boleh kosong")
        private String nomorTelepon;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AuthenticationRequest {
        @NotBlank(message = "Email tidak boleh kosong")
        private String email;
        @NotBlank(message = "Password tidak boleh kosong")
        private String password;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AuthenticationResponse {
        private String token;
    }
}

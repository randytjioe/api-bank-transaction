package com.bank.transaction.utils.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NasabahDto {
    private String nama;
    private String alamat;
    private String tanggalLahir;
    private String nomorTelepon;
}

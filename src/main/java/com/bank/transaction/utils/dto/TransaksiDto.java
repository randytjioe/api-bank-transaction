package com.bank.transaction.utils.dto;

import com.bank.transaction.model.JenisTransaksi;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransaksiDto {

    @NotBlank(message = "Jenis Transaksi tidak boleh kosong")
    private String jenisTransaksi;

    @NotBlank(message = "Jumlah tidak boleh kosong")
    private String jumlah;

    @NotBlank(message = "Rekening ID tidak boleh kosong")
    private String rekeningId;
}

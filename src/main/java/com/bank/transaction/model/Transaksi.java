package com.bank.transaction.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "transaksi")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaksi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long transaksiId;
    private LocalDate tanggal;

    @Enumerated(EnumType.STRING)
    @Column(name = "jenis_transaksi")
    private JenisTransaksi jenisTransaksi;

    private Double jumlah;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "rekening_id")
    private Rekening rekening;

}

package com.bank.transaction.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "rekening")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rekening {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long rekeningId;
    private String nomorRekening;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipe_rekening")
    private TipeRekening tipeRekening;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_rekening")
    private StatusRekening statusRekening;

    private Double saldo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "nasabah_id")
    private Nasabah nasabah;

    @OneToMany(mappedBy = "rekening", cascade = CascadeType.ALL)
    private List<Transaksi> transaksiList ;

}

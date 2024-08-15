package com.bank.transaction.repository;

import com.bank.transaction.model.Transaksi;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TransaksiRepository {
    List<Transaksi> findByRekeningId(Long rekeningId, Integer limit, Integer offset);
    Transaksi findById(Long id);
    Transaksi save(Transaksi transaksi);
}

package com.bank.transaction.repository;

import com.bank.transaction.model.Rekening;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RekeningRepository {
    List<Rekening> findByNasabahId(Long nasabahId);
    Rekening findById(Long id);
    Rekening save(Rekening rekening);
    void deleteById(Long id);
    Rekening updateSaldo(Rekening rekening);
}

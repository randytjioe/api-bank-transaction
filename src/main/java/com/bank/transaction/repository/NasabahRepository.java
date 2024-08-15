package com.bank.transaction.repository;

import com.bank.transaction.model.Nasabah;
import com.bank.transaction.utils.dto.NasabahDto;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NasabahRepository {
    Nasabah findById(Long id);
    Nasabah save(Nasabah nasabah);
    Optional<Nasabah> findByEmail(String email);
    Nasabah updateNasabah(Long id, Nasabah nasabah);
}

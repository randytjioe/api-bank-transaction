package com.bank.transaction.service;

import com.bank.transaction.model.Nasabah;
import com.bank.transaction.utils.dto.NasabahDto;

import java.util.List;

public interface NasabahService {
     Nasabah updateNasabahById(Long id, NasabahDto nasabah)  ;
     Nasabah getNasabahById(Long id);
}

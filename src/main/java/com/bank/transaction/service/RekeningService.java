package com.bank.transaction.service;

import com.bank.transaction.model.Rekening;

import java.util.List;

public interface RekeningService {
     Rekening createRekening(Rekening rekening);
     Rekening getRekeningById(Long id);
     List<Rekening> getRekeningsByNasabahId(Long nasabahId);
     Rekening deleteRekeningById(Long id);
}

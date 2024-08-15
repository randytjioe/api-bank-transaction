package com.bank.transaction.service;

import com.bank.transaction.model.JenisTransaksi;
import com.bank.transaction.model.Transaksi;
import com.bank.transaction.utils.dto.TransaksiDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TransaksiService {

     Transaksi createTransaksi(TransaksiDto transaksi);
     Transaksi getTransaksiById(Long id) ;
     Page<Transaksi> getTransaksiByRekeningId(Pageable pageable, Long rekeningId);
}

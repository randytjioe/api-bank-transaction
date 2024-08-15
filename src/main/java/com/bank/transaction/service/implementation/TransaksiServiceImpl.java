package com.bank.transaction.service.implementation;
import com.bank.transaction.model.JenisTransaksi;
import com.bank.transaction.model.Nasabah;
import com.bank.transaction.model.Rekening;
import com.bank.transaction.model.Transaksi;
import com.bank.transaction.repository.NasabahRepository;
import com.bank.transaction.repository.RekeningRepository;
import com.bank.transaction.repository.TransaksiRepository;
import com.bank.transaction.repository.implementation.TransaksiRepositoryImpl;
import com.bank.transaction.security.JwtService;
import com.bank.transaction.service.NasabahService;
import com.bank.transaction.service.TransaksiService;
import com.bank.transaction.utils.dto.TransaksiDto;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransaksiServiceImpl implements TransaksiService {

    @Autowired
    private TransaksiRepository transaksiRepository;
    private final NasabahRepository userRepository;
    private final RekeningRepository rekeningRepository;
    private final JwtService jwtService;

    private Double updateSaldo;

    @Autowired
    private HttpServletRequest request;

    @Override
    @Transactional
    public Transaksi createTransaksi(TransaksiDto req) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Invalid or missing Authorization header");
        }

        String jwt = authHeader.substring(7);
        var userEmail = jwtService.extractClaim(jwt, Claims::getSubject);
        Nasabah userDetails = userRepository.findByEmail(userEmail).orElseThrow(() -> new RuntimeException("User not found"));
        Rekening rekeningDetails = rekeningRepository.findById(Long.parseLong(req.getRekeningId()));
        var detailTransaction =  Transaksi.builder()
                .tanggal(LocalDate.now())
                .jenisTransaksi(JenisTransaksi.valueOf(req.getJenisTransaksi()))
                .jumlah(Double.parseDouble(req.getJumlah()))
                .rekening(rekeningDetails)
                .build();

        if(detailTransaction.getJenisTransaksi() == JenisTransaksi.SETOR){
             updateSaldo = rekeningDetails.getSaldo() + detailTransaction.getJumlah();
        } else {
             updateSaldo = rekeningDetails.getSaldo() - detailTransaction.getJumlah();
        }

        if( updateSaldo < 0){
            throw new RuntimeException("Saldo tidak mencukupi");
        } else {
            rekeningDetails.setSaldo(updateSaldo);
            rekeningRepository.updateSaldo(rekeningDetails);
        }
        return transaksiRepository.save(detailTransaction);
    }
    @Override
    public Transaksi getTransaksiById(Long id) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Invalid or missing Authorization header");
        }

        String jwt = authHeader.substring(7);
        var userEmail = jwtService.extractClaim(jwt, Claims::getSubject);
        Nasabah userDetails = userRepository.findByEmail(userEmail).orElseThrow(() -> new RuntimeException("User not found"));
        Transaksi transaksiDetails = transaksiRepository.findById(id);

        return transaksiRepository.findById(id);
    }
    @Override
    public Page<Transaksi> getTransaksiByRekeningId(Pageable pageable, Long rekeningId) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Invalid or missing Authorization header");
        }

        String jwt = authHeader.substring(7);
        var userEmail = jwtService.extractClaim(jwt, Claims::getSubject);
        Nasabah userDetails = userRepository.findByEmail(userEmail).orElseThrow(() -> new RuntimeException("User not found"));
        Rekening rekeningDetails = rekeningRepository.findById(rekeningId);
        List<Transaksi> transaksi = transaksiRepository.findByRekeningId(rekeningId, pageable.getPageSize(), pageable.getPageSize()*(pageable.getPageNumber()));
        int total = transaksi.size();
        return new PageImpl<>(transaksi, pageable, total);
    }
}

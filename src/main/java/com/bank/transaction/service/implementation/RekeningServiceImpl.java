package com.bank.transaction.service.implementation;
import com.bank.transaction.model.Nasabah;
import com.bank.transaction.model.Rekening;
import com.bank.transaction.model.StatusRekening;
import com.bank.transaction.repository.NasabahRepository;
import com.bank.transaction.repository.RekeningRepository;
import com.bank.transaction.security.JwtService;
import com.bank.transaction.service.NasabahService;
import com.bank.transaction.service.RekeningService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class RekeningServiceImpl implements RekeningService {

    @Autowired
    private RekeningRepository rekeningRepository;
    private final NasabahRepository userRepository;
    private final JwtService jwtService;

    @Autowired
    private HttpServletRequest request;
    @Override
    public Rekening createRekening(Rekening req) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Invalid or missing Authorization header");
        }

        String jwt = authHeader.substring(7);
        var userEmail = jwtService.extractClaim(jwt, Claims::getSubject);
        Nasabah userDetails = userRepository.findByEmail(userEmail).orElseThrow(() -> new RuntimeException("User not found"));

        var detailRekening =  Rekening.builder()
                .tipeRekening(req.getTipeRekening())
                .nomorRekening(req.getNomorRekening())
                .statusRekening(StatusRekening.AKTIF)
                .saldo(req.getSaldo())
                .nasabah(userDetails)
                .build();
        return rekeningRepository.save(detailRekening);
    }
    @Override
    public Rekening getRekeningById(Long id) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Invalid or missing Authorization header");
        }

        String jwt = authHeader.substring(7);
        var userEmail = jwtService.extractClaim(jwt, Claims::getSubject);
        Nasabah userDetails = userRepository.findByEmail(userEmail).orElseThrow(() -> new RuntimeException("User not found"));

        Rekening rekening = rekeningRepository.findById(id);
        if (!Objects.equals(rekening.getNasabah().getNasabahId(), userDetails.getNasabahId())) {
            throw new RuntimeException("Unauthorized access");
        }
        return rekening;
    }
    @Override
    public List<Rekening> getRekeningsByNasabahId(Long nasabahId) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Invalid or missing Authorization header");
        }

        String jwt = authHeader.substring(7);
        var userEmail = jwtService.extractClaim(jwt, Claims::getSubject);
        Nasabah userDetails = userRepository.findByEmail(userEmail).orElseThrow(() -> new RuntimeException("User not found"));

        if (userDetails.getNasabahId() != nasabahId) {
            throw new RuntimeException("Unauthorized access");
        }
        return rekeningRepository.findByNasabahId(nasabahId);
    }


    @Override
    public Rekening deleteRekeningById(Long id) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Invalid or missing Authorization header");
        }

        String jwt = authHeader.substring(7);
        var userEmail = jwtService.extractClaim(jwt, Claims::getSubject);
        Nasabah userDetails = userRepository.findByEmail(userEmail).orElseThrow(() -> new RuntimeException("User not found"));

        Rekening rekening = rekeningRepository.findById(id);
        if (!Objects.equals(rekening.getNasabah().getNasabahId(), userDetails.getNasabahId())) {
            throw new RuntimeException("Unauthorized access");
        }
        rekeningRepository.deleteById(id);
        return null;
    }


}


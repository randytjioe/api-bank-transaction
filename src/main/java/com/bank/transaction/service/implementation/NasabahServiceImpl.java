package com.bank.transaction.service.implementation;

import com.bank.transaction.model.Nasabah;
import com.bank.transaction.repository.NasabahRepository;
import com.bank.transaction.security.JwtService;
import com.bank.transaction.service.NasabahService;
import com.bank.transaction.utils.dto.NasabahDto;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NasabahServiceImpl implements NasabahService {

    @Autowired
    private final NasabahRepository nasabahRepository;
    private final JwtService jwtService;

    @Autowired
    private HttpServletRequest request;

    @Override
    public Nasabah updateNasabahById(Long id, NasabahDto nasabah) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Invalid or missing Authorization header");
        }

        String jwt = authHeader.substring(7);
        var userEmail = jwtService.extractClaim(jwt, Claims::getSubject);
        Nasabah userDetails = nasabahRepository.findByEmail(userEmail).orElseThrow(() -> new RuntimeException("User not found"));

        if(!userDetails.getNasabahId().equals(id)) {
            throw new RuntimeException("Unauthorized access");
        }

        var nasabahDetail = Nasabah.builder()
                .nama(nasabah.getNama())
                .alamat(nasabah.getAlamat())
                .tanggalLahir(LocalDate.parse(nasabah.getTanggalLahir()))
                .nomorTelepon(nasabah.getNomorTelepon())
                .build();

        return nasabahRepository.updateNasabah(id,nasabahDetail);
    }

    @Override
    public Nasabah getNasabahById(Long id) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Invalid or missing Authorization header");
        }

        String jwt = authHeader.substring(7);
        var userEmail = jwtService.extractClaim(jwt, Claims::getSubject);
        Nasabah userDetails = nasabahRepository.findByEmail(userEmail).orElseThrow(() -> new RuntimeException("User not found"));

        if(!userDetails.getNasabahId().equals(id)) {
            throw new RuntimeException("Unauthorized access");
        }
        return nasabahRepository.findById(id);
    }

}


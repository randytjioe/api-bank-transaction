package com.bank.transaction.service.implementation;

import com.bank.transaction.model.Nasabah;
import com.bank.transaction.repository.NasabahRepository;
import com.bank.transaction.security.JwtService;
import com.bank.transaction.service.AuthService;
import com.bank.transaction.utils.dto.AuthDto;
import com.bank.transaction.utils.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final NasabahRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public ResponseEntity<?> register(AuthDto.RegisterRequest request) {
        if (repository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Nasabah dengan email ini sudah terdaftar");
        }
        PasswordEncoder passwordEncoderAlt = new BCryptPasswordEncoder();
        var user = Nasabah.builder()
                .nama(request.getNama())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .alamat(request.getAlamat())
                .tanggalLahir(LocalDate.parse(request.getTanggalLahir()))
                .nomorTelepon(request.getNomorTelepon())
                .build();
        Nasabah savedUser = repository.save(user);
        savedUser.setPassword(null);
        return Response.renderJSON(
                savedUser,
                "New User Created",
                HttpStatus.CREATED
        );
    }

    @Override
    public AuthDto.AuthenticationResponse authenticate(AuthDto.AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        var jwtToken = jwtService.generateToken(user);
        return AuthDto.AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}

package com.bank.transaction.service;

import com.bank.transaction.utils.dto.AuthDto;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<?> register(AuthDto.RegisterRequest request);
    AuthDto.AuthenticationResponse authenticate(AuthDto.AuthenticationRequest request);
}

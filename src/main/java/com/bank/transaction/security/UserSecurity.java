package com.bank.transaction.security;


import com.bank.transaction.model.Nasabah;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class UserSecurity {

    public boolean isUser(Authentication authentication, int userId) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return false;
        }
        Object principal = authentication.getPrincipal();
        if (!(principal instanceof Nasabah)) {
            return false;
        }
        Nasabah user = (Nasabah) principal;
        return user.getNasabahId() == userId;
    }
}
package com.bank.transaction.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "nasabah")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Nasabah implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nasabahId;

    private String nama;
    @Column(unique = true, nullable = false)
    private String email;

    @JsonIgnore
    private String password;

    @JsonIgnore
    private String alamat;
    @JsonIgnore
    private LocalDate tanggalLahir;
    @JsonIgnore
    private String nomorTelepon;

    @OneToMany(mappedBy = "nasabah", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Rekening> rekeningList ;

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    @JsonIgnore
    public String getUsername() {
        return email;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }
}

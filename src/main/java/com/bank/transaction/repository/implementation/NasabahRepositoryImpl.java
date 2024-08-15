package com.bank.transaction.repository.implementation;

import com.bank.transaction.model.Nasabah;
import com.bank.transaction.repository.NasabahRepository;
import com.bank.transaction.utils.dto.NasabahDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class NasabahRepositoryImpl implements NasabahRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Nasabah findById(Long id) {
        String sql = "SELECT * FROM nasabah WHERE nasabah_id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Nasabah.class), id);
    }

    @Override
    public Nasabah save(Nasabah nasabah) {
            String sql = "INSERT INTO nasabah (nama, alamat,email,password, tanggal_lahir, nomor_telepon) VALUES (?, ?,?, ?, ?, ?)";
            jdbcTemplate.update(sql, nasabah.getNama(), nasabah.getAlamat(),nasabah.getEmail(),nasabah.getPassword(), nasabah.getTanggalLahir(), nasabah.getNomorTelepon());
        return nasabah;
    }


    @Override
    public Optional<Nasabah> findByEmail(String email) {
        String sql = "SELECT * FROM nasabah WHERE email = ?";
        try {
            Nasabah nasabah = jdbcTemplate.queryForObject(sql, new Object[]{email}, new BeanPropertyRowMapper<>(Nasabah.class));
            return Optional.of(nasabah);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Nasabah updateNasabah(Long id, Nasabah nasabah) {
        String sql = "UPDATE nasabah SET nama = ?, alamat = ?, tanggal_lahir = ?, nomor_telepon = ? WHERE nasabah_id = ?";
         jdbcTemplate.update(sql, nasabah.getNama(), nasabah.getAlamat(), nasabah.getTanggalLahir(), nasabah.getNomorTelepon(), id);
         return nasabah;
    }

}
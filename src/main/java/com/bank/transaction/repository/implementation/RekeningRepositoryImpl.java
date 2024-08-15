package com.bank.transaction.repository.implementation;

import com.bank.transaction.model.Rekening;
import com.bank.transaction.repository.RekeningRepository;
import com.bank.transaction.utils.mapper.RekeningRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RekeningRepositoryImpl implements RekeningRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Rekening> findByNasabahId(Long nasabahId) {
        String sql = "SELECT * FROM rekening WHERE nasabah_id = ? AND status_rekening = 'AKTIF'";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Rekening.class), nasabahId);
    }

    @Override
    public Rekening findById(Long id) {
        String sql = "SELECT  r.*, n.* " +
                "FROM rekening r " +
                "JOIN nasabah n ON r.nasabah_id = n.nasabah_id " +
                "WHERE r.rekening_id = ? AND r.status_rekening = 'AKTIF' ";
        return jdbcTemplate.queryForObject(sql, new RekeningRowMapper(), id);
    }

    @Override
    public Rekening save(Rekening rekening) {
        if (rekening.getRekeningId() == null) {
            String sql = "INSERT INTO rekening (nomor_rekening, tipe_rekening, saldo, status_rekening, nasabah_id) VALUES (?, ?, ?,?, ?)";
            jdbcTemplate.update(sql, rekening.getNomorRekening(), rekening.getTipeRekening().name(), rekening.getSaldo(), rekening.getStatusRekening().name(), rekening.getNasabah().getNasabahId());
        } else {
            String sql = "UPDATE rekening SET nomor_rekening = ?, tipe_rekening = ?, saldo = ?, status_rekening = ?, nasabah_id = ? WHERE rekening_id = ?";
            jdbcTemplate.update(sql, rekening.getNomorRekening(), rekening.getTipeRekening().name(), rekening.getSaldo(), rekening.getStatusRekening().name(), rekening.getNasabah().getNasabahId(), rekening.getRekeningId());
        }
        return rekening;
    }

    @Override
    public Rekening updateSaldo(Rekening rekening) {
        String sql = "UPDATE rekening SET saldo = ? WHERE rekening_id = ?";
        jdbcTemplate.update(sql, rekening.getSaldo(), rekening.getRekeningId());
        return rekening;
    }

    @Override
    public void deleteById(Long id) {
        String sql = "UPDATE rekening SET status_rekening = ? WHERE rekening_id = ?";
        jdbcTemplate.update(sql, "TIDAK_AKTIF", id);
    }
}

package com.bank.transaction.repository.implementation;

import com.bank.transaction.model.Transaksi;
import com.bank.transaction.repository.TransaksiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TransaksiRepositoryImpl implements TransaksiRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Transaksi> findByRekeningId(Long rekeningId, Integer limit, Integer offset) {
        String sql = "SELECT * FROM transaksi WHERE rekening_id = ? LIMIT ? OFFSET ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Transaksi.class), rekeningId,limit,offset);
    }

    @Override
    public Transaksi findById(Long id) {
        String sql = "SELECT * FROM transaksi WHERE transaksi_id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Transaksi.class), id);
    }

    @Override
    public Transaksi save(Transaksi transaksi) {
        if (transaksi.getTransaksiId() == null) {
            String sql = "INSERT INTO transaksi (tanggal, jenis_transaksi, jumlah, rekening_id) VALUES (?, ?, ?, ?)";
            jdbcTemplate.update(sql, transaksi.getTanggal(), transaksi.getJenisTransaksi().name(), transaksi.getJumlah(), transaksi.getRekening().getRekeningId());
        } else {
            String sql = "UPDATE transaksi SET tanggal = ?, jenis_transaksi = ?, jumlah = ?, rekening_id = ? WHERE transaksi_id = ?";
            jdbcTemplate.update(sql, transaksi.getTanggal(), transaksi.getJenisTransaksi().name(), transaksi.getJumlah(), transaksi.getRekening().getRekeningId(), transaksi.getTransaksiId());
        }
        return transaksi;
    }

}

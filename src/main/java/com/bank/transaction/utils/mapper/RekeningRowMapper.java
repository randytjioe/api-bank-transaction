package com.bank.transaction.utils.mapper;

import com.bank.transaction.model.Nasabah;
import com.bank.transaction.model.Rekening;
import com.bank.transaction.model.TipeRekening;
import org.springframework.jdbc.core.RowMapper;


import java.sql.ResultSet;
import java.sql.SQLException;

public class RekeningRowMapper implements RowMapper<Rekening> {

    @Override
    public Rekening mapRow(ResultSet rs, int rowNum) throws SQLException {
        Rekening rekening = new Rekening();
        rekening.setRekeningId(rs.getLong("rekening_id"));
        rekening.setNomorRekening(rs.getString("nomor_rekening"));
        rekening.setTipeRekening(TipeRekening.valueOf(rs.getString("tipe_rekening")));
        rekening.setSaldo(rs.getDouble("saldo"));

        Long nasabahId = rs.getLong("nasabah_id");
        if (nasabahId != null) {
            Nasabah nasabah = new Nasabah();
            nasabah.setNasabahId(nasabahId);
            rekening.setNasabah(nasabah);
            nasabah.setNama(rs.getString("nama"));
            nasabah.setEmail(rs.getString("email"));
        }
        return rekening;
    }
}


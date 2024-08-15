package com.bank.transaction.controller;

import com.bank.transaction.model.Nasabah;
import com.bank.transaction.service.implementation.NasabahServiceImpl;
import com.bank.transaction.utils.dto.NasabahDto;
import com.bank.transaction.utils.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/nasabah")
public class NasabahController {
    @Autowired
    private NasabahServiceImpl nasabahService;

    @GetMapping("/{nasabahId}")
    public Nasabah getNasabahById(@PathVariable Long nasabahId) {
        return nasabahService.getNasabahById(nasabahId);
    }

    @PutMapping("/{nasabahId}")
    public ResponseEntity<?> updateNasabah(@PathVariable Long nasabahId, @RequestBody NasabahDto nasabah) {
        return Response.renderJSON(
                nasabahService.updateNasabahById(nasabahId,nasabah),
                "Update Profile Berhasil",
                HttpStatus.OK
        );
    }
}

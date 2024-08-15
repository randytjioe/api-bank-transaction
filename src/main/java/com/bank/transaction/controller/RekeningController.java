package com.bank.transaction.controller;

import com.bank.transaction.model.Nasabah;
import com.bank.transaction.model.Rekening;
import com.bank.transaction.service.RekeningService;
import com.bank.transaction.service.implementation.NasabahServiceImpl;
import com.bank.transaction.service.implementation.RekeningServiceImpl;
import com.bank.transaction.utils.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/rekening")
public class RekeningController {
    @Autowired
    private RekeningService rekeningService;

    @GetMapping("/nasabah/{nasabahId}")
    public List<Rekening> getRekeningByNasabah(@PathVariable Long nasabahId) {
        List<Rekening> rekenings = rekeningService.getRekeningsByNasabahId(nasabahId);
        if (rekenings.isEmpty()) {
            throw new NoSuchElementException("Nasabah with ID " + nasabahId + " not found or has no rekenings.");
        }
        return rekenings;
    }

    @GetMapping("/{rekeningId}")
    public Rekening getRekeningById(@PathVariable Long rekeningId) {
        Rekening rekening = rekeningService.getRekeningById(rekeningId);
        if (rekening == null) {
            throw new NoSuchElementException("Rekening with ID " + rekeningId + " not found .");
        }
        return rekening;
    }

    @PostMapping
    public ResponseEntity<?> addRekening(@RequestBody Rekening rekening) {
        return Response.renderJSON(
                rekeningService.createRekening(rekening),
                "Tambah Rekening Berhasil",
                HttpStatus.CREATED
        );
    }


    @DeleteMapping("/{rekeningId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> deleteRekening(@PathVariable Long rekeningId) {
        Rekening rekening = rekeningService.getRekeningById(rekeningId);
        if (rekening == null) {
            throw new NoSuchElementException("Rekening dengan ID " + rekeningId + " tidak ditemukan.");
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(rekeningService.deleteRekeningById(rekeningId));
    }
}


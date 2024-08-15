package com.bank.transaction.controller;
import com.bank.transaction.model.Nasabah;
import com.bank.transaction.model.Transaksi;
import com.bank.transaction.service.TransaksiService;
import com.bank.transaction.service.implementation.NasabahServiceImpl;
import com.bank.transaction.service.implementation.TransaksiServiceImpl;
import com.bank.transaction.utils.dto.TransaksiDto;
import com.bank.transaction.utils.response.PageResponse;
import com.bank.transaction.utils.response.Response;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transaksi")
public class TransaksiController {
    @Autowired
    private TransaksiService transaksiService;

    @GetMapping("/rekening/{rekeningId}")
    public PageResponse<?> getTransaksiByRekening(@PageableDefault Pageable pageable, @PathVariable Long rekeningId) {
        return new PageResponse<>( transaksiService.getTransaksiByRekeningId(pageable,rekeningId));
    }


    @PostMapping
    public ResponseEntity<?> addTransaksi(@Valid  @RequestBody TransaksiDto transaksi) {
        return Response.renderJSON(
                transaksiService.createTransaksi(transaksi),
                "Add Transaction Record Created",
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{transaksiId}")
    public Transaksi getTransaksiById(@PathVariable Long transaksiId) {
        return transaksiService.getTransaksiById(transaksiId);
    }
}

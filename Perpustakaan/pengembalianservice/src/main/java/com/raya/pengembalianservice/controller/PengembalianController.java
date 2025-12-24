package com.raya.pengembalianservice.controller;

import com.raya.pengembalianservice.model.Pengembalian;
import com.raya.pengembalianservice.service.PengembalianService;
import com.raya.pengembalianservice.vo.ResponseTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pengembalian")
public class PengembalianController {

    @Autowired
    private PengembalianService service;

    /**
     * Endpoint untuk membuat data pengembalian baru.
     */
    @PostMapping
    public Pengembalian createPengembalian(@RequestBody Pengembalian pengembalian) {
        return service.save(pengembalian);
    }

    /**
     * Endpoint untuk mendapatkan semua data pengembalian dengan detailnya.
     */
    @GetMapping
    public List<ResponseTemplate> getAllPengembalian() {
        return service.getAllPengembalianWithDetail();
    }

    /**
     * Endpoint untuk mendapatkan satu data pengembalian berdasarkan ID dengan detailnya.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ResponseTemplate> getPengembalianById(@PathVariable Long id) {
        ResponseTemplate response = service.getPengembalianWithDetail(id);
        if (response != null) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Endpoint untuk menghapus data pengembalian berdasarkan ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePengembalian(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
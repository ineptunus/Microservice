package com.raya.peminjamanservice.controller;

import com.raya.peminjamanservice.model.Peminjaman;
import com.raya.peminjamanservice.service.PeminjamanService;
import com.raya.peminjamanservice.vo.ResponseTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/peminjaman")
public class PeminjamanController {

    @Autowired
    private PeminjamanService service;

    /**
     * GET /api/peminjaman
     * @return A list of all Peminjaman records without Anggota/Buku details.
     */
    @GetMapping
    public List<Peminjaman> getAll() {
        return service.getAll();
    }

    /**
     * GET /api/peminjaman/details
     * @return A list of all Peminjaman records, each including its Anggota and Buku details.
     */
    @GetMapping("/details")
    public List<ResponseTemplate> getAllPeminjamanWithDetail() {
        return service.getAllPeminjamanWithDetail();
    }

    /**
     * POST /api/peminjaman
     * @param peminjaman The new Peminjaman record to create.
     * @return The created Peminjaman record with a 201 CREATED status.
     */
    @PostMapping
    public ResponseEntity<Peminjaman> save(@RequestBody Peminjaman peminjaman) {
        Peminjaman savedPeminjaman = service.save(peminjaman);
        return new ResponseEntity<>(savedPeminjaman, HttpStatus.CREATED);
    }

    /**
     * GET /api/peminjaman/{id}
     * @param id The ID of the Peminjaman to retrieve.
     * @return A single Peminjaman with its Anggota and Buku details, or 404 NOT FOUND.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ResponseTemplate> getPeminjamanWithDetail(@PathVariable Long id) {
        ResponseTemplate response = service.getPeminjamanWithDetail(id);
        if (response == null || response.getPeminjaman() == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }

    /**
     * DELETE /api/peminjaman/{id}
     * @param id The ID of the Peminjaman to delete.
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
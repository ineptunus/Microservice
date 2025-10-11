package com.raya.pengembalianservice.service;

import com.raya.pengembalianservice.model.Pengembalian;
import com.raya.pengembalianservice.repository.PengembalianRepository;
import com.raya.pengembalianservice.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PengembalianService {

    @Autowired
    private PengembalianRepository repo;

    @Autowired
    private RestTemplate restTemplate;

    private static final double DENDA_PER_HARI = 1000.0;

    public List<Pengembalian> getAll() {
        return repo.findAll();
    }
    
    public Optional<Pengembalian> getById(Long id) {
        return repo.findById(id);
    }
    
    public void delete(Long id) {
        repo.deleteById(id);
    }

    /**
     * LOGIKA BARU: Menyimpan data pengembalian berdasarkan input dari user.
     * Menerima peminjamanId dan tanggalDikembalikan, lalu menghitung sisanya.
     * @param pengembalianFromInput Objek dari Postman berisi peminjamanId & tanggalDikembalikan.
     * @return Objek Pengembalian yang sudah lengkap dan tersimpan.
     */
    public Pengembalian save(Pengembalian pengembalianFromInput) {
        if (pengembalianFromInput.getPeminjamanId() == null) {
            throw new IllegalArgumentException("peminjamanId tidak boleh kosong.");
        }
        if (pengembalianFromInput.getTanggalDikembalikan() == null) {
            throw new IllegalArgumentException("tanggalDikembalikan tidak boleh kosong.");
        }

        Peminjaman peminjaman = restTemplate.getForObject(
                "http://PEMINJAMAN-SERVICE/api/peminjaman/" + pengembalianFromInput.getPeminjamanId(),
                Peminjaman.class
        );

        if (peminjaman == null) {
            throw new IllegalArgumentException("Data Peminjaman dengan ID " + pengembalianFromInput.getPeminjamanId() + " tidak ditemukan.");
        }
        if (peminjaman.getTanggalKembali() == null) {
            throw new IllegalStateException("Data Peminjaman dengan ID " + pengembalianFromInput.getPeminjamanId() + " tidak memiliki tanggal kembali yang valid (null).");
        }

        LocalDate tanggalDikembalikanOlehUser = pengembalianFromInput.getTanggalDikembalikan();
        LocalDate tanggalHarusKembali = peminjaman.getTanggalKembali();

        if (tanggalDikembalikanOlehUser.isAfter(tanggalHarusKembali)) {
            pengembalianFromInput.setTerlambat(true);
            long selisihHari = ChronoUnit.DAYS.between(tanggalHarusKembali, tanggalDikembalikanOlehUser);
            double totalDenda = selisihHari * DENDA_PER_HARI;
            pengembalianFromInput.setDenda(totalDenda);
        } else {
            pengembalianFromInput.setTerlambat(false);
            pengembalianFromInput.setDenda(0.0);
        }

        return repo.save(pengembalianFromInput);
    }

    public ResponseTemplate getPengembalianWithDetail(Long id) {
        Pengembalian pengembalian = repo.findById(id).orElse(null);
        if (pengembalian == null) return null;

        ResponseTemplate vo = new ResponseTemplate();
        vo.setPengembalian(pengembalian);

        try {
            Peminjaman peminjaman = restTemplate.getForObject(
                "http://PEMINJAMAN-SERVICE/api/peminjaman/" + pengembalian.getPeminjamanId(), Peminjaman.class);
            if (peminjaman == null) return vo;
            vo.setPeminjaman(peminjaman);

            Anggota anggota = restTemplate.getForObject(
                "http://ANGGOTA-SERVICE/api/anggota/" + peminjaman.getAnggotaId(), Anggota.class);
            vo.setAnggota(anggota);

            Buku buku = restTemplate.getForObject(
                "http://BUKU-SERVICE/api/buku/" + peminjaman.getBukuId(), Buku.class);
            vo.setBuku(buku);

        } catch (Exception e) {
            System.err.println("Error fetching details for pengembalian ID " + id + ": " + e.getMessage());
        }
        return vo;
    }

    public List<ResponseTemplate> getAllPengembalianWithDetail() {
        return repo.findAll().stream()
            .map(p -> getPengembalianWithDetail(p.getId()))
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
    }
}
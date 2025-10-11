package com.raya.peminjamanservice.service;

import com.raya.peminjamanservice.model.Peminjaman;
import com.raya.peminjamanservice.repository.PeminjamanRepository;
import com.raya.peminjamanservice.vo.Anggota;
import com.raya.peminjamanservice.vo.Buku;
import com.raya.peminjamanservice.vo.ResponseTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PeminjamanService {

    private final PeminjamanRepository peminjamanRepository;
    private final RestTemplate restTemplate;
    private final EmailService emailService;

    @Autowired
    public PeminjamanService(PeminjamanRepository peminjamanRepository, RestTemplate restTemplate, EmailService emailService) {
        this.peminjamanRepository = peminjamanRepository;
        this.restTemplate = restTemplate;
        this.emailService = emailService;
    }

    /**
     * Mengambil semua data Peminjaman tanpa detail relasinya.
     * @return List objek Peminjaman.
     */
    public List<Peminjaman> getAll() {
        return peminjamanRepository.findAll();
    }

    /**
     * Menyimpan data Peminjaman baru.
     * Tanggal kembali dihitung otomatis (tanggal pinjam + 7 hari).
     * Jika tanggal pinjam tidak diinput, akan menggunakan tanggal hari ini.
     * @param peminjaman Objek Peminjaman yang akan disimpan.
     * @return Objek Peminjaman yang sudah tersimpan.
     */
    public Peminjaman save(Peminjaman peminjaman) {
        // Step 1: Validasi dan ambil detail Anggota dan Buku dari service lain
        Anggota anggota;
        Buku buku;
        try {
            anggota = restTemplate.getForObject("http://ANGGOTA-SERVICE/api/anggota/" + peminjaman.getAnggotaId(), Anggota.class);
        } catch (HttpClientErrorException.NotFound e) {
            throw new IllegalArgumentException("Anggota with ID " + peminjaman.getAnggotaId() + " not found.");
        }

        try {
            buku = restTemplate.getForObject("http://BUKU-SERVICE/api/buku/" + peminjaman.getBukuId(), Buku.class);
        } catch (HttpClientErrorException.NotFound e) {
            throw new IllegalArgumentException("Buku with ID " + peminjaman.getBukuId() + " not found.");
        }

        // Step 2: Atur tanggal pinjam dan tanggal kembali
        LocalDate tanggalPinjamInput = peminjaman.getTanggalPinjam();

        // Jika user tidak mengirim tanggalPinjam di JSON, gunakan tanggal hari ini sebagai default
        if (tanggalPinjamInput == null) {
            tanggalPinjamInput = LocalDate.now();
            peminjaman.setTanggalPinjam(tanggalPinjamInput);
        }

        // Hitung tanggal kembali berdasarkan tanggal pinjam yang sudah ditentukan
        peminjaman.setTanggalKembali(tanggalPinjamInput.plusDays(7));
        
        Peminjaman savedPeminjaman = peminjamanRepository.save(peminjaman);

        // Step 3: Kirim notifikasi email dengan detail yang sudah diambil
        if (anggota != null && buku != null) {
            emailService.sendPeminjamanNotification(savedPeminjaman, anggota, buku);
        }
        
        return savedPeminjaman;
    }

    /**
     * Menghapus data Peminjaman berdasarkan ID.
     * @param id ID dari Peminjaman yang akan dihapus.
     */
    public void delete(Long id) {
        peminjamanRepository.deleteById(id);
    }
    
    /**
     * Mengambil detail lengkap Peminjaman beserta data Anggota dan Buku.
     * @param id ID dari Peminjaman.
     * @return ResponseTemplate yang berisi detail lengkap.
     */
    public ResponseTemplate getPeminjamanWithDetail(Long id) {
        Peminjaman peminjaman = peminjamanRepository.findById(id).orElse(null);
        if (peminjaman == null) {
            return null;
        }

        Anggota anggota = null;
        try {
            anggota = restTemplate.getForObject("http://ANGGOTA-SERVICE/api/anggota/" + peminjaman.getAnggotaId(), Anggota.class);
        } catch (HttpClientErrorException.NotFound e) {
            System.err.println("Anggota not found for id: " + peminjaman.getAnggotaId());
        }

        Buku buku = null;
        try {
            buku = restTemplate.getForObject("http://BUKU-SERVICE/api/buku/" + peminjaman.getBukuId(), Buku.class);
        } catch (HttpClientErrorException.NotFound e) {
            System.err.println("Buku not found for id: " + peminjaman.getBukuId());
        }

        ResponseTemplate vo = new ResponseTemplate();
        vo.setPeminjaman(peminjaman);
        vo.setAnggota(anggota);
        vo.setBuku(buku);

        return vo;
    }
    
    /**
     * Mengambil semua data Peminjaman beserta detail lengkapnya.
     * @return List dari ResponseTemplate.
     */
    public List<ResponseTemplate> getAllPeminjamanWithDetail() {
        List<Peminjaman> allPeminjaman = peminjamanRepository.findAll();
        List<ResponseTemplate> responseList = new ArrayList<>();

        for (Peminjaman peminjaman : allPeminjaman) {
            ResponseTemplate vo = getPeminjamanWithDetail(peminjaman.getId());
            if (vo != null) {
                responseList.add(vo);
            }
        }
        return responseList;
    }
}
package com.raya.peminjamanservice.model;

// FIX: Change 'javax' to 'jakarta' in all imports
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;

@Entity
public class Peminjaman {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate tanggalPinjam;
    private LocalDate tanggalKembali;
    private Long anggotaId; // This field should exist from our previous fix
    private Long bukuId;    // This field should also exist

    // --- Make sure you have all Getters and Setters ---

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDate getTanggalPinjam() { return tanggalPinjam; }
    public void setTanggalPinjam(LocalDate tanggalPinjam) { this.tanggalPinjam = tanggalPinjam; }
    public LocalDate getTanggalKembali() { return tanggalKembali; }
    public void setTanggalKembali(LocalDate tanggalKembali) { this.tanggalKembali = tanggalKembali; }
    public Long getAnggotaId() { return anggotaId; }
    public void setAnggotaId(Long anggotaId) { this.anggotaId = anggotaId; }
    public Long getBukuId() { return bukuId; }
    public void setBukuId(Long bukuId) { this.bukuId = bukuId; }
}
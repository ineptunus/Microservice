package com.raya.peminjamanservice.peminjamanservice.dto;

import java.time.LocalDate;

public class PeminjamanEventDTO {
    private Long peminjamanId;
    private String anggotaNama;
    private String anggotaEmail;
    private String bukuJudul;
    private LocalDate tanggalPinjam;
    private LocalDate tanggalKembali;

    public PeminjamanEventDTO() {}

    public PeminjamanEventDTO(Long peminjamanId, String anggotaNama, String anggotaEmail, String bukuJudul, LocalDate tanggalPinjam, LocalDate tanggalKembali) {
        this.peminjamanId = peminjamanId;
        this.anggotaNama = anggotaNama;
        this.anggotaEmail = anggotaEmail;
        this.bukuJudul = bukuJudul;
        this.tanggalPinjam = tanggalPinjam;
        this.tanggalKembali = tanggalKembali;
    }

    // Getters and Setters
    public Long getPeminjamanId() {
        return peminjamanId;
    }

    public void setPeminjamanId(Long peminjamanId) {
        this.peminjamanId = peminjamanId;
    }

    public String getAnggotaNama() {
        return anggotaNama;
    }

    public void setAnggotaNama(String anggotaNama) {
        this.anggotaNama = anggotaNama;
    }

    public String getAnggotaEmail() {
        return anggotaEmail;
    }

    public void setAnggotaEmail(String anggotaEmail) {
        this.anggotaEmail = anggotaEmail;
    }

    public String getBukuJudul() {
        return bukuJudul;
    }

    public void setBukuJudul(String bukuJudul) {
        this.bukuJudul = bukuJudul;
    }

    public LocalDate getTanggalPinjam() {
        return tanggalPinjam;
    }

    public void setTanggalPinjam(LocalDate tanggalPinjam) {
        this.tanggalPinjam = tanggalPinjam;
    }

    public LocalDate getTanggalKembali() {
        return tanggalKembali;
    }

    public void setTanggalKembali(LocalDate tanggalKembali) {
        this.tanggalKembali = tanggalKembali;
    }
}
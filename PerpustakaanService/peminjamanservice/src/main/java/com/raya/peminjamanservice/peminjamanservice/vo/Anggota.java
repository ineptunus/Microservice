package com.raya.peminjamanservice.peminjamanservice.vo;

// INI HANYA WADAH DATA SEDERHANA (POJO)
public class Anggota {
    // Field-field ini harus sama persis dengan yang ada di model Anggota
    // di dalam proyek anggotaservice.
    private Long id;
    private String nim;
    private String nama;
    private String email;
    private String alamat;
    private String jenisKelamin;

    public Anggota() {}

    public Anggota(Long id, String nim, String nama, String email, String alamat, String jenisKelamin) {
        this.id = id;
        this.nim = nim;
        this.nama = nama;
        this.email = email;
        this.alamat = alamat;
        this.jenisKelamin = jenisKelamin;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }
}
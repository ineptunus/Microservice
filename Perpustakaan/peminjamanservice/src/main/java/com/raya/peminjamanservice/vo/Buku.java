package com.raya.peminjamanservice.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// INI JUGA HANYA WADAH DATA SEDERHANA (POJO)
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Buku {
    private Long id;
    private String judul;
    private String pengarang;
    private String penerbit;
    private Integer tahunTerbit;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getJudul() { return judul; }
    public void setJudul(String judul) { this.judul = judul; }
}

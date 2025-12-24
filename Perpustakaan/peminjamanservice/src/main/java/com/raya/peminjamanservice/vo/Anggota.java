package com.raya.peminjamanservice.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Anggota {
    private Long id;
    private String nim;
    private String nama;
    private String alamat;
    private String jenisKelamin;
    private String email;


    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

}

package com.raya.pengembalianservice.vo;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Anotasi @Data akan membuat semua getter dan setter secara otomatis
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Peminjaman {
    private Long id;
    private LocalDate tanggalPinjam;
    private LocalDate tanggalKembali; // Pastikan field ini ada!
    private Long anggotaId;
    private Long bukuId;
}
package com.raya.pengembalianservice.vo;

import com.raya.pengembalianservice.model.Pengembalian;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseTemplate {
    private Pengembalian pengembalian;
    private Peminjaman peminjaman;
    private Anggota anggota;
    private Buku buku;

    // === ADD THESE SETTERS MANUALLY TO FIX IDE ERRORS ===
    public void setPengembalian(Pengembalian pengembalian) {
        this.pengembalian = pengembalian;
    }

    public void setPeminjaman(Peminjaman peminjaman) {
        this.peminjaman = peminjaman;
    }

    public void setAnggota(Anggota anggota) {
        this.anggota = anggota;
    }

    public void setBuku(Buku buku) {
        this.buku = buku;
    }
}
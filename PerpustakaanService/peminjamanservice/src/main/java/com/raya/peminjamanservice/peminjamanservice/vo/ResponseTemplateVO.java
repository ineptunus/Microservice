package com.raya.peminjamanservice.peminjamanservice.vo;

import com.raya.peminjamanservice.peminjamanservice.model.Peminjaman;

public class ResponseTemplateVO {
    private Peminjaman peminjaman;
    private Anggota anggota;
    private Buku buku;

    public ResponseTemplateVO() {}

    public ResponseTemplateVO(Peminjaman peminjaman, Anggota anggota, Buku buku) {
        this.peminjaman = peminjaman;
        this.anggota = anggota;
        this.buku = buku;
    }

    // Getters and Setters
    public Peminjaman getPeminjaman() {
        return peminjaman;
    }

    public void setPeminjaman(Peminjaman peminjaman) {
        this.peminjaman = peminjaman;
    }

    public Anggota getAnggota() {
        return anggota;
    }

    public void setAnggota(Anggota anggota) {
        this.anggota = anggota;
    }

    public Buku getBuku() {
        return buku;
    }

    public void setBuku(Buku buku) {
        this.buku = buku;
    }
}
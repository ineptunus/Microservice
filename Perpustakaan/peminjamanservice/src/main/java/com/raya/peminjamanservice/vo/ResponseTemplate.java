package com.raya.peminjamanservice.vo;

import com.raya.peminjamanservice.model.Peminjaman;

// Using Lombok is a great way to avoid writing this boilerplate code,
// but for now, let's write it manually.

public class ResponseTemplate {
    private Peminjaman peminjaman;
    private Anggota anggota;
    private Buku buku;

    // Default constructor (important for JSON mapping)
    public ResponseTemplate() {
    }

    // --- GETTERS (methods to get the data) ---
    public Peminjaman getPeminjaman() {
        return peminjaman;
    }

    public Anggota getAnggota() {
        return anggota;
    }

    public Buku getBuku() {
        return buku;
    }


    // --- SETTERS (methods to set the data) ---
    // !!! THIS IS THE PART YOU ARE MISSING !!!
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
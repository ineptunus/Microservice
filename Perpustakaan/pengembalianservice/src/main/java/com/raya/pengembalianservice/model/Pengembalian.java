package com.raya.pengembalianservice.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Pengembalian {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate tanggalDikembalikan;
    private Boolean terlambat;
    private Double denda;
    private Long peminjamanId;

    // === ADD THIS GETTER MANUALLY TO FIX IDE ERROR ===
    public Long getPeminjamanId() {
        return this.peminjamanId;
    }
    
    // You can also add the ID getter for consistency
    public Long getId() {
        return this.id;
    }
}
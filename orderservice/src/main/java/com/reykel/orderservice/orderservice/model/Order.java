package com.reykel.orderservice.orderservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders") // hindari pakai "order"
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long produkId;
    private Long pelangganId;
    private int jumlah;
    private String tanggal;
    private String status;
    private double total;
}

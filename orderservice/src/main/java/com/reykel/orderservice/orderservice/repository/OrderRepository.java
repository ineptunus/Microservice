package com.reykel.orderservice.orderservice.repository;

import com.reykel.orderservice.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    // Bisa tambahkan query custom jika perlu, contoh:
    // List<Order> findByPelangganId(Long pelangganId);
}

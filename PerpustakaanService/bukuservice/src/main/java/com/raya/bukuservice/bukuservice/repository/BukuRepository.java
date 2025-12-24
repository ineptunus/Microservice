package com.raya.bukuservice.bukuservice.repository;

import com.raya.bukuservice.bukuservice.model.Buku;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BukuRepository extends JpaRepository<Buku, Long> {
}
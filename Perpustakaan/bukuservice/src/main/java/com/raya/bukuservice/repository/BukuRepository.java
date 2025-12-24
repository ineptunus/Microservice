package com.raya.bukuservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.raya.bukuservice.model.Buku;

@Repository
public interface BukuRepository extends JpaRepository<Buku, Long> {
}

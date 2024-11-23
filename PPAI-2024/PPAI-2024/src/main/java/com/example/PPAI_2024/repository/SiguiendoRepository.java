package com.example.PPAI_2024.repository;

import com.example.PPAI_2024.entity.Siguiendo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SiguiendoRepository extends JpaRepository<Siguiendo, Long> {
}
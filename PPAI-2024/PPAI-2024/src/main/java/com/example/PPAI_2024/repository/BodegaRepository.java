package com.example.PPAI_2024.repository;

import com.example.PPAI_2024.entity.Bodega;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BodegaRepository extends JpaRepository<Bodega, Long> {

    List<Bodega> findByNombre(String nombre);
    
}


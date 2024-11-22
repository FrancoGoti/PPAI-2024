package com.example.PPAI_2024.repository;

import com.example.PPAI_2024.entity.Bodega;
import com.example.PPAI_2024.entity.Vino;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VinoRepository extends JpaRepository<Vino, Long> {

    List<Vino> findVinosByBodegaId(Long bodegaId);
    
    List<Vino> findVinosByBodega(Bodega bodega);
    
    
}

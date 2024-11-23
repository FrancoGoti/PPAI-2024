package com.example.PPAI_2024.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.PPAI_2024.entity.Enofilo; 

public interface EnofiloRepository extends JpaRepository<Enofilo, Long> {

    List<Enofilo> findByBodegaId(Long bodegaId);

}

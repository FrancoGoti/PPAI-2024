package com.example.PPAI_2024.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.PPAI_2024.entity.Enofilo; 

public interface EnofiloRepository extends JpaRepository<Enofilo, Long> {

    // List<Enofilo> findByBodegaId(Long bodegaId);

    
    // @Query("SELECT e FROM Enofilo e JOIN e.siguiendo s WHERE s.bodega = :bodega")
    // List<Enofilo> findByBodegaId(@Param("bodegaId") Long bodegaId);
}

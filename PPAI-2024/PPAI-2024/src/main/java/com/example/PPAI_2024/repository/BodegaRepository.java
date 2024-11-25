package com.example.PPAI_2024.repository;

import com.example.PPAI_2024.entity.Bodega;
import com.example.PPAI_2024.entity.Vino;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BodegaRepository extends JpaRepository<Bodega, Long> {

    List<Bodega> findByNombre(String nombre);

//     @Query("SELECT b.vinos FROM Bodega b WHERE b = :bodega")
//     List<Vino> listarVinosPorBodega(@Param("bodega") Bodega bodega);
// 
    @Query("SELECT v FROM Vino v JOIN v.bodegas b WHERE b.id = :bodegaId")
    List<Vino> findVinosByBodegaId(@Param("bodegaId") Long bodegaId);

    List<Bodega> findByVinos(Vino vino);

}





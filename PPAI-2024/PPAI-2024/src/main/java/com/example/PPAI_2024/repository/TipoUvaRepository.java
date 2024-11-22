package com.example.PPAI_2024.repository;

import com.example.PPAI_2024.entity.TipoUva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoUvaRepository extends JpaRepository<TipoUva, Long> {
}

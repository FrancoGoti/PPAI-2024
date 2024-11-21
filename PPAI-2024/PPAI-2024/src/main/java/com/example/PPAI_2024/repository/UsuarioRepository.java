package com.example.PPAI_2024.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.PPAI_2024.entity.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Métodos adicionales si son necesarios, como buscar por nombre
}
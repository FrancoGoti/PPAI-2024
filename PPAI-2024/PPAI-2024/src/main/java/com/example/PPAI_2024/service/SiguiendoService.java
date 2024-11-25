package com.example.PPAI_2024.service;

import com.example.PPAI_2024.entity.Siguiendo;
import com.example.PPAI_2024.repository.SiguiendoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SiguiendoService {

    private final SiguiendoRepository siguiendoRepository;

    public SiguiendoService(SiguiendoRepository siguiendoRepository) {
        this.siguiendoRepository = siguiendoRepository;
    }

    public List<Siguiendo> getAllSiguiendo() {
        return siguiendoRepository.findAll();
    }

    public Siguiendo getSiguiendoById(Long id) {
        return siguiendoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Siguiendo no encontrado con ID: " + id));
    }

    public Siguiendo saveSiguiendo(Siguiendo siguiendo) {
        return siguiendoRepository.save(siguiendo);
    }

    public void deleteSiguiendo(Long id) {
        siguiendoRepository.deleteById(id);
    }

    public Siguiendo actualizarDatos(Long id, Siguiendo siguiendoActualizado) {
        Siguiendo siguiendoExistente = getSiguiendoById(id);
        siguiendoExistente.setBodega(siguiendoActualizado.getBodega()); // Actualizar los atributos necesarios
        return siguiendoRepository.save(siguiendoExistente);
    }

    
}
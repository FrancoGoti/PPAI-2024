package com.example.PPAI_2024.service;

import com.example.PPAI_2024.entity.Maridaje;
import com.example.PPAI_2024.repository.MaridajeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MaridajeService {

    private final MaridajeRepository maridajeRepository;

    public MaridajeService(MaridajeRepository maridajeRepository) {
        this.maridajeRepository = maridajeRepository;
    }

    // Obtener todos los maridajes
    public List<Maridaje> getAllMaridajes() {
        return maridajeRepository.findAll();
    }

    // Obtener un maridaje por ID
    public Optional<Maridaje> getMaridajeById(Long id) {
        return maridajeRepository.findById(id);
    }

    // Crear o actualizar un maridaje
    public Maridaje saveMaridaje(Maridaje maridaje) {
        return maridajeRepository.save(maridaje);
    }

    // Eliminar un maridaje por ID
    public void deleteMaridaje(Long id) {
        maridajeRepository.deleteById(id);
    }
}

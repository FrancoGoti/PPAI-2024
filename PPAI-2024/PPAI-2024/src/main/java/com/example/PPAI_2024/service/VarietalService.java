package com.example.PPAI_2024.service;

import com.example.PPAI_2024.entity.Varietal;
import com.example.PPAI_2024.repository.VarietalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VarietalService {

    private final VarietalRepository varietalRepository;

    public VarietalService(VarietalRepository varietalRepository) {
        this.varietalRepository = varietalRepository;
    }

    // Obtener todos los varietales
    public List<Varietal> getAllVarietals() {
        return varietalRepository.findAll();
    }

    // Obtener un varietal por ID
    public Optional<Varietal> getVarietalById(Long id) {
        return varietalRepository.findById(id);
    }

    // Crear o actualizar un varietal
    public Varietal saveVarietal(Varietal varietal) {
        return varietalRepository.save(varietal);
    }

    // Eliminar un varietal por ID
    public void deleteVarietal(Long id) {
        varietalRepository.deleteById(id);
    }
}

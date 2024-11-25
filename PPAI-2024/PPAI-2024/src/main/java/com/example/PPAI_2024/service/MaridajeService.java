package com.example.PPAI_2024.service;

import com.example.PPAI_2024.entity.Maridaje;
import com.example.PPAI_2024.repository.MaridajeRepository;
import org.springframework.stereotype.Service;
import java.util.List;


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
    public Maridaje getMaridajeById(Long id) {
        return maridajeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Maridaje no encontrado con ID: " + id));
    }

    // Crear o actualizar un maridaje
    public Maridaje saveMaridaje(Maridaje maridaje) {
        return maridajeRepository.save(maridaje);
    }

    // Eliminar un maridaje por ID
    public void deleteMaridaje(Long id) {
        maridajeRepository.deleteById(id);
    }

    public Maridaje actualizarDatos(Long id, Maridaje maridajeActualizado){
        Maridaje maridajeExistente = getMaridajeById(id);
        maridajeExistente.setNombre(maridajeActualizado.getNombre());
        maridajeExistente.setDescripcion(maridajeActualizado.getDescripcion());
        return maridajeRepository.save(maridajeExistente);

    }
}

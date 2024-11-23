package com.example.PPAI_2024.service;

import com.example.PPAI_2024.entity.Varietal;
import com.example.PPAI_2024.repository.VarietalRepository;
import org.springframework.stereotype.Service;
import java.util.List;


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
    public Varietal getVarietalById(Long id) {
        return varietalRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Maridaje no encontrado con ID: " + id));
    }

    // Crear o actualizar un varietal
    public Varietal saveVarietal(Varietal varietal) {
        return varietalRepository.save(varietal);
    }

    // Eliminar un varietal por ID
    public void deleteVarietal(Long id) {
        varietalRepository.deleteById(id);
    }

    public Varietal actualizarDatos(Long id, Varietal varietalActualizado){
        Varietal varietalExistente = getVarietalById(id);
        varietalExistente.setTipoUva(varietalActualizado.getTipoUva());
        varietalExistente.setDescripcion(varietalActualizado.getDescripcion());
        varietalExistente.setPorcentajeComposicion(varietalActualizado.getPorcentajeComposicion());
        return varietalRepository.save(varietalExistente);
    }
}

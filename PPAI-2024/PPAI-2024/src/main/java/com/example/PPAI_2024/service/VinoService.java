package com.example.PPAI_2024.service;

import com.example.PPAI_2024.entity.Vino;
import com.example.PPAI_2024.repository.VinoRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class VinoService {

    private final VinoRepository vinoRepository;

    public VinoService(VinoRepository vinoRepository) {
        this.vinoRepository = vinoRepository;
    }

    // Obtener todos los vinos
    public List<Vino> obtenerTodos() {
        return vinoRepository.findAll();
    }

    // Guardar un vino nuevo
    public Vino guardar(Vino vino) {
        return vinoRepository.save(vino);
    }

    // Obtener un vino por su ID
    public Vino obtenerPorId(Long id) {
        return vinoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Vino no encontrado con ID: " + id));
    }

    // Actualizar los datos de un vino
    public Vino actualizarDatos(Long id, Vino vinoActualizado) {
        Vino vinoExistente = obtenerPorId(id);

        // Actualizar los campos
        vinoExistente.setAñada(vinoActualizado.getAñada());
        vinoExistente.setFechaActualizacion(vinoActualizado.getFechaActualizacion());
        vinoExistente.setNombre(vinoActualizado.getNombre());
        vinoExistente.setPrecio(vinoActualizado.getPrecio());
        vinoExistente.setNotaDeCataBodega(vinoActualizado.getNotaDeCataBodega());
        vinoExistente.setMaridaje(vinoActualizado.getMaridaje());
        vinoExistente.setVarietal(vinoActualizado.getVarietal());

        return vinoRepository.save(vinoExistente);
    }

    // Eliminar un vino por su ID
    public void eliminar(Long id) {
        if (!vinoRepository.existsById(id)) {
            throw new IllegalArgumentException("Vino no encontrado con ID: " + id);
        }
        vinoRepository.deleteById(id);
    }

    public List<Vino> getVinosByBodega(Long bodegaId) {
        return vinoRepository.findByBodegas_Id(bodegaId);
    }

}
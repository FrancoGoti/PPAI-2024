package com.example.PPAI_2024.service;

import com.example.PPAI_2024.entity.TipoUva;
import com.example.PPAI_2024.entity.Varietal;
import com.example.PPAI_2024.entity.Vino;
import com.example.PPAI_2024.repository.VinoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class VinoService {

    private final VinoRepository vinoRepository;

    @Autowired
    private TipoUvaService tipoUvaService;

    @Autowired
    private VarietalService varietalService;

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
        vinoExistente.setAniada(vinoActualizado.getAniada());
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

    public void actualizarVino(Vino vino) {
        Varietal varietal = vino.getVarietal();
        TipoUva tipoUva = varietal.getTipoUva();
    
        // Si el TipoUva no está guardado, guardarlo primero (cascada en caso de tenerla configurada)
        if (tipoUva != null && tipoUva.getId() == null) {
            tipoUvaService.saveTipoUva(tipoUva);  // Guarda el TipoUva si es nuevo
        }
    
        // Si el Varietal no está guardado, guardarlo primero (esto puede también hacer cascada si está configurado)
        if (varietal != null && varietal.getId() == null) {
            varietalService.saveVarietal(varietal);  // Guarda el Varietal si es nuevo
        }
    
        // Guardar el Vino (se guarda siempre, porque puede ser nuevo o actualizarse)
        vinoRepository.save(vino);  // Hibernate maneja la relación entre las entidades
    }

}
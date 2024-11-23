package com.example.PPAI_2024.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.PPAI_2024.entity.Bodega;
import com.example.PPAI_2024.entity.Enofilo;
import com.example.PPAI_2024.entity.Vino;
import com.example.PPAI_2024.repository.BodegaRepository;
import com.example.PPAI_2024.repository.EnofiloRepository;
import com.example.PPAI_2024.repository.VinoRepository;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class GestorImportacionVinoService {

     @Autowired
    private BodegaRepository bodegaRepository;

    @Autowired
    private EnofiloRepository enofiloRepository;

    @Autowired
    private VinoRepository vinoRepository;

    public GestorImportacionVinoService(BodegaRepository bodegaRepository) {
        this.bodegaRepository = bodegaRepository;
    }

    /**
     * Buscar las bodegas con actualizaciones disponibles.
     */
    @Autowired
    private BodegaService bodegaService; // Inyectar el servicio
    
    public List<Bodega> buscarBodegasActDisponible() {
        LocalDate fechaActual = buscarFechaActual().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate(); // Convertir la fecha actual una vez.
    
        List<Bodega> bodegas = bodegaRepository.findAll();
    
        List<Bodega> bodegasConActDisponible = bodegas.stream()
            .filter(b -> b.getFechaActualizacion() != null) // Validar que no sea null
            .filter(b -> bodegaService.tieneActualizacionDisponible(
                b, 
                b.getFechaActualizacion(), // Usar directamente el LocalDate
                fechaActual
            ))
            .toList();
    
        return bodegasConActDisponible;
    }
    
    
    
    

    public void actualizarVinosBodega(Long bodegaId, List<Vino> nuevosVinos) {
        Bodega bodega = bodegaRepository.findById(bodegaId)
            .orElseThrow(() -> new RuntimeException("Bodega no encontrada"));

        bodega.setVinosBodega(nuevosVinos);
        bodegaRepository.save(bodega);
    }

    
    public void buscarEnofilosSuscriptos(Long bodegaId) {
        List<Enofilo> enofilos = enofiloRepository.findByBodegaId(bodegaId);
        // Lógica para notificar a los enófilos...
    }

    public Date buscarFechaActual() {
        return new Date();
    }

    /**
     * Setear la bodega seleccionada (caso de uso futuro).
     */
    public void tomarSelBodega(Long bodegaSeleccionada) {
        // Lógica de selección podría ser almacenada o procesada aquí
        System.out.println("Bodega seleccionada: " + bodegaSeleccionada);
    }

    // MOSTRAR LOS VINOS DE LA BODEGA SELECCIONADA ACTUALIZADOS
     public List<Vino> obtenerResumenVinosActualizados(Long bodegaId) {
        return vinoRepository.findVinosByBodegaId(bodegaId);
    }
}

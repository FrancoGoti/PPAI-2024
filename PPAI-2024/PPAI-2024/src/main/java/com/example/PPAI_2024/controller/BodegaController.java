package com.example.PPAI_2024.controller;

import com.example.PPAI_2024.entity.Bodega;
import com.example.PPAI_2024.entity.Vino;
import com.example.PPAI_2024.service.BodegaService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/bodegas")
public class BodegaController {

    private final BodegaService bodegaService;

    public BodegaController(BodegaService bodegaService) {
        this.bodegaService = bodegaService;
    }

    @GetMapping("/{id}/actualizacion-disponible")
    public boolean verificarActualizacion(
            @PathVariable Long id,
            @RequestParam LocalDate fechaBodega,
            @RequestParam LocalDate fechaActualComparacion) {
        // Aquí deberías obtener la bodega desde el repositorio (omitido por simplicidad)
        Bodega bodega = obtenerBodegaPorId(id);
        return bodegaService.tieneActualizacionDisponible(bodega, fechaBodega, fechaActualComparacion);
    }

    @PostMapping("/{id}/actualizar-vinos")
    public void actualizarVinos(
            @PathVariable Long id,
            @RequestParam Date fechaActual,
            @RequestBody List<Vino> actualizacionesDeBodega) {
        // Aquí deberías obtener la bodega desde el repositorio (omitido por simplicidad)
        Bodega bodega = obtenerBodegaPorId(id);
        bodegaService.actualizarDatosVinosBodega(bodega, fechaActual, actualizacionesDeBodega);
    }

    // Método simulado para obtener la bodega por ID
    private Bodega obtenerBodegaPorId(Long id) {
        // Simulación: reemplazar por una consulta al repositorio
        return new Bodega();
    }
}


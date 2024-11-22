package com.example.PPAI_2024.controller;

import com.example.PPAI_2024.entity.Maridaje;
import com.example.PPAI_2024.entity.Varietal;
import com.example.PPAI_2024.entity.Vino;
import com.example.PPAI_2024.service.VinoService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/vinos")
public class VinoController {

    private final VinoService vinoService;

    public VinoController(VinoService vinoService) {
        this.vinoService = vinoService;
    }

    @PutMapping("/{id}/actualizar")
    public void actualizarVino(
            @PathVariable Long id,
            @RequestParam int añada,
            @RequestParam LocalDate fechaActualizacion,
            @RequestParam String nombre,
            @RequestParam float precio,
            @RequestParam String notaDeCataBodega,
            @RequestBody Maridaje maridaje,
            @RequestBody Varietal varietal) {
        // Aquí deberías obtener el vino desde el repositorio (omitido por simplicidad)
        Vino vino = obtenerVinoPorId(id);
        vinoService.actualizarDatos(vino, añada, fechaActualizacion,  nombre,  precio,
         notaDeCataBodega,  maridaje,  varietal);
    }

    // Método simulado para obtener el vino por ID
    private Vino obtenerVinoPorId(Long id) {
        // Simulación: reemplazar con una consulta al repositorio
        return new Vino();
    }
}

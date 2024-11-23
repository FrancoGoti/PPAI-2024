package com.example.PPAI_2024.controller;

import com.example.PPAI_2024.entity.Bodega;
import com.example.PPAI_2024.entity.Vino;
import com.example.PPAI_2024.service.BodegaService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/bodegas")
public class BodegaController {

    private final BodegaService bodegaService;

    public BodegaController(BodegaService bodegaService) {
        this.bodegaService = bodegaService;
    }

    @GetMapping
    public String listarBodegas(Model model) {
        List<Bodega> bodegas = bodegaService.obtenerBodegas();
        model.addAttribute("bodegas", bodegas);
        return "bodegas";
    }

    @GetMapping("/nuevo")
    public String nuevaBodega(Model model) {
        Bodega nuevaBodega = new Bodega();
        model.addAttribute("bodega", nuevaBodega);
        model.addAttribute("modo", "nuevo");
        return "formulario-bodega";
    }

    @PostMapping("/guardar")
    public String guardarVino(@ModelAttribute Bodega bodega) {
        bodegaService.guardar(bodega);
        return "redirect:/bodegas";
    }

    @GetMapping("/editar/{id}")
    public String editarBodega(@PathVariable Long id, Model model) {
        Bodega bodega = bodegaService.obtenerPorId(id);
        model.addAttribute("bodega", bodega);
        model.addAttribute("modo", "editar");
        return "formulario-bodega";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizarBodega(@PathVariable Long id, @ModelAttribute Bodega bodega) {
        bodegaService.actualizarDatos(id, bodega);
        return "redirect:/bodegas";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarBodega(@PathVariable Long id) {
        bodegaService.eliminar(id);
        return "redirect:/bodegas";
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
            @RequestParam LocalDate fechaActual,
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


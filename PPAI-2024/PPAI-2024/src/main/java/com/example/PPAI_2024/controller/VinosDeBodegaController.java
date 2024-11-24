package com.example.PPAI_2024.controller;

import com.example.PPAI_2024.entity.Bodega;
import com.example.PPAI_2024.entity.Vino;
import com.example.PPAI_2024.service.BodegaService;
import com.example.PPAI_2024.service.VinoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/vinosDeBodega")
public class VinosDeBodegaController {

    private final BodegaService bodegaService;

    @Autowired
    private VinoService vinoService;

    public VinosDeBodegaController(BodegaService bodegaService) {
        this.bodegaService = bodegaService;
    }

    // Método para listar vinos por bodega
    @GetMapping("/{bodegaId}/vinos")
    public String listarVinos(@PathVariable Long bodegaId, Model model) {
        List<Vino> vinos = vinoService.getVinosByBodega(bodegaId);
        model.addAttribute("vinos", vinos);
        return "vinosPorBodega";
    }

    // Método para mostrar el formulario de vinos de una bodega
    @GetMapping
    public String mostrarFormulario(@RequestParam Long bodegaId, @RequestParam String modo, Model model) {
        Bodega bodega = bodegaService.obtenerPorId(bodegaId);
        List<Vino> vinosDeBodega = vinoService.getVinosByBodega(bodegaId);  // Aquí llamamos a getVinosByBodega

        model.addAttribute("bodega", bodega);
        model.addAttribute("vinosDeBodega", vinosDeBodega);  // Usamos solo los vinos de esa bodega
        model.addAttribute("modo", modo);
        return "vinosPorBodega";
    }
    
    // Método para asignar vino a una bodega
    @PostMapping("/asignar")
    public String asignarVinoABodega(@RequestParam Long bodegaId, @RequestParam Long vinoId) {
        Bodega bodega = bodegaService.obtenerPorId(bodegaId);
        Vino vino = vinoService.obtenerPorId(vinoId);

        bodegaService.asignarVino(bodega, vino);
        return "redirect:/vinosDeBodega?bodegaId=" + bodegaId;
    }

    // Método para quitar vino de una bodega
    @PostMapping("/quitar")
    public String quitarVinoDeBodega(@RequestParam Long bodegaId, @RequestParam Long vinoId) {
        Bodega bodega = bodegaService.obtenerPorId(bodegaId);
        Vino vino = vinoService.obtenerPorId(vinoId);

        bodegaService.quitarVino(bodega, vino);
        return "redirect:/vinosDeBodega?bodegaId=" + bodegaId;
    }
}

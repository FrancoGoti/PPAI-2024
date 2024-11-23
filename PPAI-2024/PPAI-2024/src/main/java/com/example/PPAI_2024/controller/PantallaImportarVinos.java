package com.example.PPAI_2024.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.PPAI_2024.entity.Bodega;
import com.example.PPAI_2024.service.GestorImportacionVinoService;
import org.springframework.ui.Model;


@Controller
@RequestMapping("/pantalla-importar-vinos")
public class PantallaImportarVinos {

    private final GestorImportacionVinoService gestor;

    public PantallaImportarVinos(GestorImportacionVinoService gestor) {
        this.gestor = gestor;
    }

    // Método para mostrar la pantalla principal
    @GetMapping
    public String mostrarPantalla(Model model) {
        model.addAttribute("bodegas", gestor.buscarBodegasActDisponible());
        return "pantalla-importar-vinos"; // Nombre del archivo HTML
    }

    // Método para seleccionar bodega
    @PostMapping("/seleccionar-bodega")
    public String tomarSeleccionBodega(@RequestParam("bodegaId") Bodega bodegaSeleccionada, Model model) {
        gestor.tomarSelBodega(bodegaSeleccionada);
        model.addAttribute("vinos", gestor.obtenerResumenVinosActualizados(bodegaSeleccionada));
        return "resumen-vinos"; // Página HTML con el resumen
    }

    // Método para importar actualizaciones
    @PostMapping("/importar")
    public String importarActualizaciones(@RequestParam("bodegaId") Long bodegaId, Model model) {
        gestor.actualizarVinosBodega(bodegaId, null);
        return "redirect:/pantalla-importar-vinos";
    }

}
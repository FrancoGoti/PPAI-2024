package com.example.PPAI_2024.controller;

import com.example.PPAI_2024.entity.Maridaje;
import com.example.PPAI_2024.service.MaridajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/maridajes")
public class MaridajeController {

    @Autowired
    private MaridajeService maridajeService;

    // Listar todos los maridajes
    @GetMapping
    public String listarMaridajes(Model model) {
        var maridajes = maridajeService.getAllMaridajes();
        model.addAttribute("maridajes", maridajes);
        return "maridajes"; // Nombre de la vista HTML para listar
    }

    // Mostrar formulario para agregar un nuevo maridaje
    @GetMapping("/nuevo")
    public String agregarMaridaje(Model model) {
        model.addAttribute("maridaje", new Maridaje());
        model.addAttribute("modo", "nuevo");
        return "formulario-maridaje"; // Nombre de la vista HTML para el formulario
    }

    // Guardar un nuevo maridaje
    @PostMapping("/guardar")
    public String guardarMaridaje( Maridaje maridaje, BindingResult error, Model model) {
        if (error.hasErrors()) {
            model.addAttribute("modo", "nuevo");
            return "formulario-maridaje";
        }
        maridajeService.saveMaridaje(maridaje);
        return "redirect:/maridajes";
    }

    // Mostrar formulario para editar un maridaje existente
    @GetMapping("/editar/{id}")
    public String editarFormulario(@PathVariable Long id, Model model) {
        var maridaje = maridajeService.getMaridajeById(id).orElse(null);
        if (maridaje == null) {
            return "redirect:/maridajes";
        }
        model.addAttribute("maridaje", maridaje);
        model.addAttribute("modo", "editar");
        return "formulario-maridaje";
    }

    // Actualizar un maridaje
    @PostMapping("/actualizar")
    public String actualizarMaridaje( Maridaje maridaje, BindingResult error, Model model) {
        if (error.hasErrors()) {
            model.addAttribute("modo", "editar");
            return "formulario-maridaje";
        }
        maridajeService.saveMaridaje(maridaje);
        return "redirect:/maridajes";
    }

    // Eliminar un maridaje
    @GetMapping("/eliminar/{id}")
    public String eliminarMaridaje(@PathVariable Long id) {
        maridajeService.deleteMaridaje(id);
        return "redirect:/maridajes";
    }
}
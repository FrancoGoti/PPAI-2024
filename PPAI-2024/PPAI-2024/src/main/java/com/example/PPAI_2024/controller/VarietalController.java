package com.example.PPAI_2024.controller;

import com.example.PPAI_2024.entity.Varietal;
import com.example.PPAI_2024.service.VarietalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/varietal")
public class VarietalController {

    @Autowired
    private VarietalService varietalService;

    // Listar todos los varietales
    @GetMapping
    public String listarVarietal(Model model) {
        var varietal = varietalService.getAllVarietals();
        model.addAttribute("varietal", varietal);
        return "varietal"; // Vista con los varietales
    }

    // Mostrar formulario para agregar un varietal
    @GetMapping("/nuevo")
    public String agregarVarietal(Model model) {
        var varietal = new Varietal();
        model.addAttribute("varietal", varietal);
        model.addAttribute("modo", "nuevo");
        return "formulario-varietal"; // Vista del formulario para agregar un varietal
    }

    // Guardar un nuevo varietal
    @PostMapping("/guardar")
    public String guardarVarietal(Varietal varietal, BindingResult error, Model model) {
        if (error.hasErrors()) {
            model.addAttribute("modo", "nuevo");
            return "formulario-varietal"; // Regresar al formulario si hay errores
        }
        varietalService.saveVarietal(varietal);
        return "redirect:/varietal"; // Redirigir a la lista de varietales
    }

    // Mostrar formulario para editar un varietal
    @GetMapping("/editar/{id}")
    public String editarVarietal(@PathVariable Long id, Model model) {
        var varietal = varietalService.getVarietalById(id).orElse(null);
        if (varietal != null) {
            model.addAttribute("varietal", varietal);
            model.addAttribute("modo", "editar");
            return "formulario-varietal"; // Vista del formulario para editar
        }
        return "redirect:/varietal"; // Redirigir si no se encuentra el varietal
    }

    // Actualizar un varietal
    @PostMapping("/actualizar")
    public String actualizarVarietal(Varietal varietal, BindingResult error, Model model) {
        if (error.hasErrors()) {
            model.addAttribute("modo", "editar");
            return "formulario-varietal"; // Regresar al formulario si hay errores
        }
        varietalService.saveVarietal(varietal);
        return "redirect:/varietal"; // Redirigir a la lista de varietales
    }

    // Eliminar un varietal
    @GetMapping("/eliminar/{id}")
    public String eliminarVarietal(@PathVariable Long id) {
        varietalService.deleteVarietal(id);
        return "redirect:/varietal"; // Redirigir a la lista de varietales
    }
}
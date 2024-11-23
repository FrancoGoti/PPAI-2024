package com.example.PPAI_2024.controller;

import com.example.PPAI_2024.entity.Vino;
import com.example.PPAI_2024.service.VinoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
@RequestMapping("/vinos")
public class VinoController {

    private final VinoService vinoService;

    public VinoController(VinoService vinoService) {
        this.vinoService = vinoService;
    }

    // Listar todos los vinos
    @GetMapping
    public String listarVinos(Model model) {
        List<Vino> vinos = vinoService.obtenerTodos();
        model.addAttribute("vinos", vinos);
        return "vinos";
    }

    // Mostrar formulario para agregar un nuevo vino
    @GetMapping("/nuevo")
    public String nuevoVino(Model model) {
        model.addAttribute("vino", new Vino());
        return "formulario-vino";
    }

    // Guardar un nuevo vino
    @PostMapping("/guardar")
    public String guardarVino(@ModelAttribute Vino vino) {
        vinoService.guardar(vino);
        return "redirect:/vinos";
    }

    // Mostrar formulario para editar un vino
    @GetMapping("/editar/{id}")
    public String editarVino(@PathVariable Long id, Model model) {
        Vino vino = vinoService.obtenerPorId(id);
        model.addAttribute("vino", vino);
        return "formulario-vino";
    }

    // Actualizar un vino existente
    @PostMapping("/actualizar/{id}")
    public String actualizarVino(@PathVariable Long id, @ModelAttribute Vino vino) {
        vinoService.actualizarDatos(id, vino);
        return "redirect:/vinos";
    }

    // Eliminar un vino
    @GetMapping("/eliminar/{id}")
    public String eliminarVino(@PathVariable Long id) {
        vinoService.eliminar(id);
        return "redirect:/vinos";
    }
}

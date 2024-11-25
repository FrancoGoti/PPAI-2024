package com.example.PPAI_2024.controller;

import com.example.PPAI_2024.entity.TipoUva;
import com.example.PPAI_2024.service.TipoUvaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tipo-uvas")
public class TipoUvaController {

    private final TipoUvaService tipoUvaService;

    public TipoUvaController(TipoUvaService tipoUvaService) {
        this.tipoUvaService = tipoUvaService;
    }

    // Listar todos los tipos de uva
    @GetMapping
    public String listarTipoUvas(Model model) {
        var tipoUvas = tipoUvaService.getAllTipoUvas();
        model.addAttribute("tipoUvas", tipoUvas);
        return "tipo-uvas"; // Vista para listar tipos de uva
    }

    // Mostrar formulario para agregar un tipo de uva
    @GetMapping("/nuevo")
    public String agregarTipoUva(Model model) {
        var tipoUva = new TipoUva();
        model.addAttribute("tipoUva", tipoUva);
        model.addAttribute("modo", "nuevo");
        return "formulario-tipo-uva"; // Vista del formulario
    }

    // Guardar un nuevo tipo de uva
    @PostMapping("/guardar")
    public String guardarTipoUva(@ModelAttribute TipoUva tipoUva, BindingResult error, Model model) {
        if (error.hasErrors()) {
            model.addAttribute("modo", "nuevo");
            return "formulario-tipo-uva";
        }
        tipoUvaService.saveTipoUva(tipoUva);
        return "redirect:/tipo-uvas";
    }

    // Mostrar formulario para editar un tipo de uva
    @GetMapping("/editar/{id}")
    public String editarTipoUva(@PathVariable Long id, Model model) {
        var tipoUva = tipoUvaService.getTipoUvaById(id);
        if (tipoUva != null) {
            model.addAttribute("tipoUva", tipoUva);  // Pasar el objeto completo
            model.addAttribute("modo", "editar");
            return "formulario-tipo-uva";
        } else {
            return "redirect:/tipo-uvas";  // Asegúrate que la redirección esté a la URL correcta
        }
    }
    
    // Actualizar un tipo de uva
    @PostMapping("/actualizar/{id}")
    public String actualizarTipoUva(@PathVariable Long id, @ModelAttribute TipoUva tipoUva) {
        tipoUvaService.actualizarDatos(id, tipoUva);
        return "redirect:/tipo-uvas";
    }

    // Eliminar un tipo de uva
    @GetMapping("/eliminar/{id}")
    public String eliminarTipoUva(@PathVariable Long id) {
        tipoUvaService.deleteTipoUva(id);
        return "redirect:/tipo-uvas";
    }
}


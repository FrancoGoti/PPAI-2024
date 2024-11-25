package com.example.PPAI_2024.controller;

import com.example.PPAI_2024.entity.Maridaje;
import com.example.PPAI_2024.entity.Varietal;
import com.example.PPAI_2024.entity.Vino;
import com.example.PPAI_2024.service.MaridajeService;
import com.example.PPAI_2024.service.VarietalService;
import com.example.PPAI_2024.service.VinoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/vinos")
public class VinoController {

    private final VinoService vinoService;
    private final MaridajeService maridajeService;
    private final VarietalService varietalService;

    public VinoController(VinoService vinoService, MaridajeService maridajeService, VarietalService varietalService) {
        this.vinoService = vinoService;
        this.maridajeService = maridajeService;
        this.varietalService = varietalService;
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
        Vino nuevoVino = new Vino();
        nuevoVino.setVarietal(new Varietal()); // Inicializa Varietal
        nuevoVino.setMaridaje(new Maridaje()); // Inicializa Maridaje
    
        model.addAttribute("vino", nuevoVino);
        model.addAttribute("maridajesDisponibles", maridajeService.getAllMaridajes());
        model.addAttribute("varietalesDisponibles", varietalService.getAllVarietals());
    
        return "formulario-vino";
    }

    // Guardar un nuevo vino
    @PostMapping("/guardar")
    public String guardarVino(@ModelAttribute Vino vino) {
        LocalDate fechaActualizacion = LocalDate.now();
        vino.setFechaActualizacion(fechaActualizacion);
        vinoService.guardar(vino);
        return "redirect:/vinos";
    }

    // Mostrar formulario para editar un vino
    @GetMapping("/editar/{id}")
    public String editarVino(@PathVariable Long id, Model model) {
        Vino vino = vinoService.obtenerPorId(id);
    
        // Inicializa relaciones si están vacías
        if (vino.getVarietal() == null) {
            vino.setVarietal(new Varietal());
        }
        if (vino.getMaridaje() == null) {
            vino.setMaridaje(new Maridaje());
        }
    
        model.addAttribute("vino", vino);
        model.addAttribute("maridajesDisponibles", maridajeService.getAllMaridajes());
        model.addAttribute("varietalesDisponibles", varietalService.getAllVarietals());
    
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

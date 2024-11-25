package com.example.PPAI_2024.controller;

import com.example.PPAI_2024.entity.Siguiendo;
import com.example.PPAI_2024.service.SiguiendoService;
import com.example.PPAI_2024.service.BodegaService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/siguiendo")
public class SiguiendoController {

    @Autowired
    private SiguiendoService siguiendoService;

    @Autowired
    private BodegaService bodegaService;

    // Listar todos los registros
    @GetMapping
    public String listarSiguiendo(Model model) {
        List<Siguiendo> siguiendo = siguiendoService.getAllSiguiendo();
        model.addAttribute("siguiendos", siguiendo);
        return "siguiendo"; // Vista que muestra los registros de "Siguiendo"
    }

    // Mostrar formulario para agregar un nuevo registro
    @GetMapping("/nuevo")
    public String agregarSiguiendo(Model model) {
        var siguiendo = new Siguiendo();
        var bodegas = bodegaService.obtenerBodegas(); // Obtener todas las bodegas
        model.addAttribute("siguiendo", siguiendo);
        model.addAttribute("bodegas", bodegas); // Pasar las bodegas al modelo
        model.addAttribute("modo", "nuevo");
        return "formulario-siguiendo"; // Vista del formulario
    }

    // Guardar un nuevo seguido
    @PostMapping("/guardar")
    public String guardarSiguiendo(Siguiendo siguiendo, BindingResult error, Model model) {
        if (error.hasErrors()) {
            var bodegas = bodegaService.obtenerBodegas(); // Asegurar que las bodegas están disponibles en caso de error
            model.addAttribute("bodegas", bodegas);
            model.addAttribute("modo", "nuevo");
            return "formulario-siguiendo";
        }
        siguiendoService.saveSiguiendo(siguiendo);
        return "redirect:/siguiendo";
    }

    // Mostrar formulario para editar un registro existente
    @GetMapping("/editar/{id}")
    public String editarSiguiendo(@PathVariable Long id, Model model) {
        var siguiendo = siguiendoService.getSiguiendoById(id);
        if (siguiendo != null) {
            var bodegas = bodegaService.obtenerBodegas(); // Obtener todas las bodegas para el formulario
            var bodega = siguiendo.getBodega();
            model.addAttribute("siguiendo", siguiendo);
            model.addAttribute("bodegas", bodegas);
            model.addAttribute("bodega", bodega);
            model.addAttribute("modo", "editar");
            return "formulario-siguiendo";
        }
        return "redirect:/siguiendo"; // Redirigir si no se encuentra el registro
    }

    // Actualizar un registro existente
    @PostMapping("/actualizar/{id}")
    public String actualizarSiguiendo(@PathVariable Long id, @ModelAttribute Siguiendo siguiendo) {
        siguiendoService.actualizarDatos(id, siguiendo);
        return "redirect:/siguiendo";
    }

    // Eliminar un registro
    @GetMapping("/eliminar/{id}")
    public String eliminarSiguiendo(@PathVariable Long id) {
        siguiendoService.deleteSiguiendo(id);
        return "redirect:/siguiendo";
    }
}
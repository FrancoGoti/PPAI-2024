package com.example.PPAI_2024.controller;

import com.example.PPAI_2024.entity.Maridaje;
import com.example.PPAI_2024.service.MaridajeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/maridajes")
public class MaridajeController {

    private final MaridajeService maridajeService;

    public MaridajeController(MaridajeService maridajeService) {
        this.maridajeService = maridajeService;
    }

    // Obtener todos los maridajes
    @GetMapping
    public List<Maridaje> getAllMaridajes() {
        return maridajeService.getAllMaridajes();
    }

    // Obtener un maridaje por ID
    @GetMapping("/{id}")
    public ResponseEntity<Maridaje> getMaridajeById(@PathVariable Long id) {
        Optional<Maridaje> maridaje = maridajeService.getMaridajeById(id);
        return maridaje.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear un nuevo maridaje
    @PostMapping
    public Maridaje createMaridaje(@RequestBody Maridaje maridaje) {
        return maridajeService.saveMaridaje(maridaje);
    }

    // Actualizar un maridaje existente
    @PutMapping("/{id}")
    public ResponseEntity<Maridaje> updateMaridaje(@PathVariable Long id, @RequestBody Maridaje maridajeDetails) {
        Optional<Maridaje> existingMaridaje = maridajeService.getMaridajeById(id);
        if (existingMaridaje.isPresent()) {
            Maridaje updatedMaridaje = existingMaridaje.get();
            updatedMaridaje.setNombre(maridajeDetails.getNombre());
            updatedMaridaje.setDescripcion(maridajeDetails.getDescripcion());
            return ResponseEntity.ok(maridajeService.saveMaridaje(updatedMaridaje));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar un maridaje
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMaridaje(@PathVariable Long id) {
        if (maridajeService.getMaridajeById(id).isPresent()) {
            maridajeService.deleteMaridaje(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

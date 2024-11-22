package com.example.PPAI_2024.controller;

import com.example.PPAI_2024.entity.TipoUva;
import com.example.PPAI_2024.service.TipoUvaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tipo-uvas")
public class TipoUvaController {

    private final TipoUvaService tipoUvaService;

    public TipoUvaController(TipoUvaService tipoUvaService) {
        this.tipoUvaService = tipoUvaService;
    }

    // Obtener todos los tipos de uva
    @GetMapping
    public List<TipoUva> getAllTipoUvas() {
        return tipoUvaService.getAllTipoUvas();
    }

    // Obtener un tipo de uva por ID
    @GetMapping("/{id}")
    public ResponseEntity<TipoUva> getTipoUvaById(@PathVariable Long id) {
        Optional<TipoUva> tipoUva = tipoUvaService.getTipoUvaById(id);
        return tipoUva.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear un nuevo tipo de uva
    @PostMapping
    public TipoUva createTipoUva(@RequestBody TipoUva tipoUva) {
        return tipoUvaService.saveTipoUva(tipoUva);
    }

    // Actualizar un tipo de uva existente
    @PutMapping("/{id}")
    public ResponseEntity<TipoUva> updateTipoUva(@PathVariable Long id, @RequestBody TipoUva tipoUvaDetails) {
        Optional<TipoUva> existingTipoUva = tipoUvaService.getTipoUvaById(id);
        if (existingTipoUva.isPresent()) {
            TipoUva updatedTipoUva = existingTipoUva.get();
            updatedTipoUva.setNombre(tipoUvaDetails.getNombre());
            updatedTipoUva.setDescripcion(tipoUvaDetails.getDescripcion());
            return ResponseEntity.ok(tipoUvaService.saveTipoUva(updatedTipoUva));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar un tipo de uva
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTipoUva(@PathVariable Long id) {
        if (tipoUvaService.getTipoUvaById(id).isPresent()) {
            tipoUvaService.deleteTipoUva(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

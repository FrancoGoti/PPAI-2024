package com.example.PPAI_2024.controller;

import com.example.PPAI_2024.entity.Varietal;
import com.example.PPAI_2024.service.VarietalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/varietals")
public class VarietalController {

    private final VarietalService varietalService;

    public VarietalController(VarietalService varietalService) {
        this.varietalService = varietalService;
    }

    // Obtener todos los varietales
    @GetMapping
    public List<Varietal> getAllVarietals() {
        return varietalService.getAllVarietals();
    }

    // Obtener un varietal por ID
    @GetMapping("/{id}")
    public ResponseEntity<Varietal> getVarietalById(@PathVariable Long id) {
        Optional<Varietal> varietal = varietalService.getVarietalById(id);
        return varietal.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear un nuevo varietal
    @PostMapping
    public Varietal createVarietal(@RequestBody Varietal varietal) {
        return varietalService.saveVarietal(varietal);
    }

    // Actualizar un varietal existente
    @PutMapping("/{id}")
    public ResponseEntity<Varietal> updateVarietal(@PathVariable Long id, @RequestBody Varietal varietalDetails) {
        Optional<Varietal> existingVarietal = varietalService.getVarietalById(id);
        if (existingVarietal.isPresent()) {
            Varietal updatedVarietal = existingVarietal.get();
            updatedVarietal.setDescripcion(varietalDetails.getDescripcion());
            updatedVarietal.setPorcentajeComposicion(varietalDetails.getPorcentajeComposicion());
            updatedVarietal.setTipoUva(varietalDetails.getTipoUva());
            return ResponseEntity.ok(varietalService.saveVarietal(updatedVarietal));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar un varietal
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVarietal(@PathVariable Long id) {
        if (varietalService.getVarietalById(id).isPresent()) {
            varietalService.deleteVarietal(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

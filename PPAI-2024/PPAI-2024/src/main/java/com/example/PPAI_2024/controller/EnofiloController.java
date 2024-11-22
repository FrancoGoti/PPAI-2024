package com.example.PPAI_2024.controller;

import org.springframework.web.bind.annotation.*;

import com.example.PPAI_2024.entity.Enofilo;
import com.example.PPAI_2024.service.EnofiloService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/enofilos")
public class EnofiloController {

    private final EnofiloService enofiloService;

    public EnofiloController(EnofiloService enofiloService) {
        this.enofiloService = enofiloService;
    }

    @GetMapping
    public List<Enofilo> listarTodos() {
        return enofiloService.listarTodos();
    }

    @GetMapping("/{id}")
    public Optional<Enofilo> obtenerPorId(@PathVariable Long id) {
        return enofiloService.obtenerPorId(id);
    }

    @PostMapping
    public Enofilo guardar(@RequestBody Enofilo enofilo) {
        return enofiloService.guardar(enofilo);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        enofiloService.eliminar(id);
    }
}


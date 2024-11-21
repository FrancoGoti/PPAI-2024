package com.example.PPAI_2024.service;

import org.springframework.stereotype.Service;

import com.example.PPAI_2024.entity.Enofilo;
import com.example.PPAI_2024.repository.EnofiloRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EnofiloService {

    private final EnofiloRepository enofiloRepository;

    public EnofiloService(EnofiloRepository enofiloRepository) {
        this.enofiloRepository = enofiloRepository;
    }

    public List<Enofilo> listarTodos() {
        return enofiloRepository.findAll();
    }

    public Optional<Enofilo> obtenerPorId(Long id) {
        return enofiloRepository.findById(id);
    }

    public Enofilo guardar(Enofilo enofilo) {
        return enofiloRepository.save(enofilo);
    }

    public void eliminar(Long id) {
        enofiloRepository.deleteById(id);
    }
}


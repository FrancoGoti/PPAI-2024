package com.example.PPAI_2024.service;

import org.springframework.stereotype.Service;

import com.example.PPAI_2024.entity.Enofilo;

import com.example.PPAI_2024.repository.EnofiloRepository;

import java.util.List;

@Service
public class EnofiloService {

    private final EnofiloRepository enofiloRepository;

    public EnofiloService(EnofiloRepository enofiloRepository) {
        this.enofiloRepository = enofiloRepository;
    }

    public List<Enofilo> listarTodos() {
        return enofiloRepository.findAll();
    }

    public Enofilo obtenerPorId(Long id) {
        return enofiloRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Enofilo no encontrado con ID: " + id));
    }

    public Enofilo guardar(Enofilo enofilo) {
        return enofiloRepository.save(enofilo);
    }

    public void eliminarEnofilo(Long id) {
        enofiloRepository.deleteById(id);
    }

    public Enofilo actualizarDatos(Long id, Enofilo enofiloActualizado){
        Enofilo enofiloExistente = obtenerPorId(id);
        enofiloExistente.setNombre(enofiloActualizado.getNombre());
        enofiloExistente.setApellido(enofiloActualizado.getApellido());
        enofiloExistente.setSeguido(enofiloActualizado.getSeguido());
        enofiloExistente.setUsuario(enofiloActualizado.getUsuario());
        enofiloExistente.setFavorito(enofiloActualizado.getFavorito());
        return enofiloRepository.save(enofiloExistente);
    }
}


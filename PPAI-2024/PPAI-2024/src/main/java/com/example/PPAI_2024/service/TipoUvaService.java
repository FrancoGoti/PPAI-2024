package com.example.PPAI_2024.service;

import com.example.PPAI_2024.entity.TipoUva;
import com.example.PPAI_2024.repository.TipoUvaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoUvaService {

    private final TipoUvaRepository tipoUvaRepository;

    public TipoUvaService(TipoUvaRepository tipoUvaRepository) {
        this.tipoUvaRepository = tipoUvaRepository;
    }

    // Obtener todos los tipos de uva
    public List<TipoUva> getAllTipoUvas() {
        return tipoUvaRepository.findAll();
    }

    // Obtener un tipo de uva por ID
    public Optional<TipoUva> getTipoUvaById(Long id) {
        return tipoUvaRepository.findById(id);
    }

    // Crear o actualizar un tipo de uva
    public TipoUva saveTipoUva(TipoUva tipoUva) {
        return tipoUvaRepository.save(tipoUva);
    }

    // Eliminar un tipo de uva por ID
    public void deleteTipoUva(Long id) {
        tipoUvaRepository.deleteById(id);
    }
}

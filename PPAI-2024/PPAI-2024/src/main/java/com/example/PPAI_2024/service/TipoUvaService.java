package com.example.PPAI_2024.service;

import com.example.PPAI_2024.entity.TipoUva;
import com.example.PPAI_2024.repository.TipoUvaRepository;
import org.springframework.stereotype.Service;
import java.util.List;


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
    public TipoUva getTipoUvaById(Long id) {
        return tipoUvaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("TipoUva no encontrado con ID: " + id));
    }

    // Crear o actualizar un tipo de uva
    public TipoUva saveTipoUva(TipoUva tipoUva) {
        return tipoUvaRepository.save(tipoUva);
    }

    // Eliminar un tipo de uva por ID
    public void deleteTipoUva(Long id) {
        tipoUvaRepository.deleteById(id);
    }

    public TipoUva actualizarDatos(Long id,TipoUva tipoUvaActualizado){
        TipoUva tipoUvaExistente = getTipoUvaById(id);
        tipoUvaExistente.setNombre(tipoUvaActualizado.getNombre());
        tipoUvaExistente.setDescripcion(tipoUvaActualizado.getDescripcion());
        return tipoUvaRepository.save(tipoUvaExistente);
    }
}

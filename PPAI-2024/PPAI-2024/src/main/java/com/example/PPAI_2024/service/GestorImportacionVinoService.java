package com.example.PPAI_2024.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.PPAI_2024.entity.Bodega;
import com.example.PPAI_2024.entity.Vino;
import com.example.PPAI_2024.repository.BodegaRepository;
// import com.example.PPAI_2024.repository.EnofiloRepository;
import com.example.PPAI_2024.repository.VinoRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class GestorImportacionVinoService {

    @Autowired
    private BodegaRepository bodegaRepository;

    // @Autowired
    // private EnofiloRepository enofiloRepository;

    @Autowired
    private VinoRepository vinoRepository;

    public GestorImportacionVinoService(BodegaRepository bodegaRepository) {
        this.bodegaRepository = bodegaRepository;
    }

    public List<Bodega> buscarBodegasActDisponible() {
        LocalDate fechaActual = buscarFechaActual();
        List<Bodega> bodegas = bodegaRepository.findAll();
    
        return bodegas.stream()
            .filter(b -> b.tieneActualizacionDisponible(b.getFechaActualizacion(), fechaActual))
            .toList();
    }

    public void actualizarVinosBodega(Long bodegaId, List<Vino> nuevosVinos) {
        Bodega bodega = bodegaRepository.findById(bodegaId)
            .orElseThrow(() -> new RuntimeException("Bodega no encontrada"));

        bodega.setVinosBodega(nuevosVinos);
        bodegaRepository.save(bodega);
    }

    // public void buscarEnofilosSuscriptos(Long bodegaId) {
    //     List<Enofilo> enofilos = enofiloRepository.findByBodegaId(bodegaId);

    //     // Simulación de notificación
    //     enofilos.forEach(enofilo -> {
    //         System.out.println("Notificando a enófilo: " + enofilo.getNombre());
    //     });
    // }

    public LocalDate buscarFechaActual() {
        return LocalDate.now();
    }

    public String tomarSelBodega(Bodega bodegaSeleccionada) {
        System.out.println("Bodega seleccionada: " + bodegaSeleccionada);
        return bodegaSeleccionada.getNombre();
    }

    public List<Vino> obtenerResumenVinosActualizados(Bodega bodega) {
        return vinoRepository.findByBodegas(bodega);
    }

    // private LocalDate convertirDateALocalDate(Date date) {
    //     if (date == null) {
    //         return null; // Manejo de nulos si es necesario
    //     }
    //     return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    // }
}
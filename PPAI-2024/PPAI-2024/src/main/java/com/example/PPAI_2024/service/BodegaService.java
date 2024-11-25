package com.example.PPAI_2024.service;

import com.example.PPAI_2024.entity.Bodega;
import com.example.PPAI_2024.entity.Vino;
import com.example.PPAI_2024.repository.BodegaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class BodegaService {

    private final BodegaRepository bodegaRepository;

    public BodegaService(BodegaRepository bodegaRepository) {
            this.bodegaRepository = bodegaRepository;
    }
    
    @Autowired
    private VinoService vinoService;
    /**
     * Verifica si una bodega tiene actualizaciones disponibles según la fecha y el periodo.
     */
    public boolean tieneActualizacionDisponible(Bodega bodega, LocalDate fechaBodega, LocalDate fechaActualComparacion) {
        long diferenciaFechas = ChronoUnit.MONTHS.between(fechaBodega, fechaActualComparacion);
        return diferenciaFechas > bodega.getPeriodoActualizacion();
    }

    /**
     * Actualiza los datos de los vinos de la bodega.
     */
    // public void actualizarDatosVinosBodega(Bodega bodega, LocalDate fechaActual, List<Vino> actualizacionesDeBodega) {
    //     bodega.setFechaActualizacion(fechaActual);
    
    //     for (Vino actualizacion : actualizacionesDeBodega) {
    //         for (Vino vino : bodega.getVinosBodega()) {
    //             if (vino.getNombre().equals(actualizacion.getNombre())) {
    //                 // Asumiendo que "vino" tiene el ID que necesita el método de VinoService
    //                 actualizacion.setFechaActualizacion(fechaActual); // Actualiza la fecha en el objeto "actualizacion"
    //                 vinoService.actualizarDatos(vino.getId(), actualizacion);
    //             }
    //         }
    //     }
    // }


    public void actualizarDatosVinosBodega(Bodega bodegaSeleccionada, List<Vino> vinosActualizados) {
        LocalDate fechaActual = LocalDate.now();
        
        // Lógica para actualizar los vinos en memoria
        for (int i = 0; i < vinosActualizados.size(); i++) {
            boolean vinoEncontrado = false;
    
            // Iterar sobre los vinos de la bodega
            for (int j = 0; j < bodegaSeleccionada.getVinosBodega().size(); j++) {
                Vino vinoExistente = bodegaSeleccionada.getVinosBodega().get(j);
                if (vinoExistente.getNombre().equals(vinosActualizados.get(i).getNombre())) {
                    // Actualizar los datos del vino
                    vinoExistente.actualizarDatos(
                        vinosActualizados.get(i).getAniada(),
                        fechaActual,
                        vinosActualizados.get(i).getNombre(),
                        vinosActualizados.get(i).getPrecio(),
                        vinosActualizados.get(i).getNotaDeCataBodega(),
                        vinosActualizados.get(i).getMaridaje(),
                        vinosActualizados.get(i).getVarietal()
                    );
                    vinoEncontrado = true;
                    break;
                }
            }
    
            // Si no lo encuentras en la bodega, lo añades
            if (!vinoEncontrado) {
                // Guardar el vino en la base de datos si es nuevo
                if (vinosActualizados.get(i).getId() == null) {
                    vinoService.guardar(vinosActualizados.get(i)); // Guardar el nuevo vino
                }
                // Añadir el nuevo vino a la bodega
                bodegaSeleccionada.getVinosBodega().add(vinosActualizados.get(i));  
            }
        }
    
        // Persistir los cambios de vinos en la base de datos
        for (Vino vino : bodegaSeleccionada.getVinosBodega()) {
            vinoService.actualizarVino(vino);  // Persistir el vino
        }
    }



    
    public List<Bodega> obtenerBodegas(){
        return bodegaRepository.findAll();
    }

    public Bodega guardar(Bodega bodega){
        return bodegaRepository.save(bodega);
    }

    public Bodega obtenerPorId(Long id) {
        return bodegaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Vino no encontrado con ID: " + id));
    }

    public Bodega actualizarDatos(Long id, Bodega bodegaActualizada){
        Bodega bodegaExistente = obtenerPorId(id);
        bodegaExistente.setNombre(bodegaActualizada.getNombre());
        bodegaExistente.setDescripcion(bodegaActualizada.getDescripcion());
        bodegaExistente.setCoordenadas(bodegaActualizada.getCoordenadas());
        bodegaExistente.setHistoria(bodegaActualizada.getHistoria());
        bodegaExistente.setPeriodoActualizacion(bodegaActualizada.getPeriodoActualizacion());
        bodegaExistente.setFechaActualizacion(bodegaActualizada.getFechaActualizacion());
        return bodegaRepository.save(bodegaExistente);
    }

    public void eliminar(Long id) {
        if (!bodegaRepository.existsById(id)) {
            throw new IllegalArgumentException("Vino no encontrado con ID: " + id);
        }
        bodegaRepository.deleteById(id);
    }

    public List<Vino> obtenerVinosPorBodega(Bodega bodega){
        return bodegaRepository.findVinosByBodegaId(bodega.getId());
    }

    public void asignarVino(Bodega bodega, Vino vino) {
        bodega.getVinosBodega().add(vino); // Asegúrate de que `vinos` sea una lista o colección en Bodega.
        guardar(bodega); // Persistir los cambios.
    }

    public void quitarVino(Bodega bodega, Vino vino) {
        bodega.getVinosBodega().remove(vino);
        bodegaRepository.save(bodega); // Persistir los cambios.
    }
 
}


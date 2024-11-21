package com.example.PPAI_2024.service;

import com.example.PPAI_2024.entity.Bodega;
import com.example.PPAI_2024.entity.Vino;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

@Service
public class BodegaService {

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
    public void actualizarDatosVinosBodega(Bodega bodega, Date fechaActual, List<Vino> actualizacionesDeBodega) {
        bodega.setFechaActualizacion(fechaActual);

        for (Vino actualizacion : actualizacionesDeBodega) {
            for (Vino vino : bodega.getVinosBodega()) {
                if (vino.getNombre().equals(actualizacion.getNombre())) {
                    vino.actualizarDatos(
                            actualizacion.getAñada(),
                            fechaActual,
                            actualizacion.getNombre(),
                            actualizacion.getPrecio(),
                            actualizacion.getNotaDeCataBodega(),
                            actualizacion.getMaridaje(),
                            actualizacion.getVarietal()
                    );
                }
            }
        }
    }
}


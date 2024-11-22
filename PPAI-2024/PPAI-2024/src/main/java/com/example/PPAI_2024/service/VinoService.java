package com.example.PPAI_2024.service;

import com.example.PPAI_2024.entity.Maridaje;
import com.example.PPAI_2024.entity.Varietal;
import com.example.PPAI_2024.entity.Vino;
import org.springframework.stereotype.Service;
import java.time.LocalDate;


@Service
public class VinoService {

    /**
     * Actualiza los datos de un vino.
     */
    public void actualizarDatos(Vino vino, int añada, LocalDate fechaActualizacion, String nombre, float precio,
                                String notaDeCataBodega, Maridaje maridaje, Varietal varietal) {
        vino.setAñada(añada);
        vino.setFechaActualizacion(fechaActualizacion);
        vino.setNombre(nombre);
        vino.setPrecio(precio);
        vino.setNotaDeCataBodega(notaDeCataBodega);
        vino.setMaridaje(maridaje);
        vino.setVarietal(varietal);
    }
}

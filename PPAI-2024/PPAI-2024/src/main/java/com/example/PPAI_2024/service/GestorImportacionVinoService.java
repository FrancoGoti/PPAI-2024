package com.example.PPAI_2024.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.PPAI_2024.entity.Bodega;
import com.example.PPAI_2024.entity.Vino;
import com.example.PPAI_2024.entity.VinoApiService;
import com.example.PPAI_2024.repository.BodegaRepository;
// import com.example.PPAI_2024.repository.EnofiloRepository;
import java.time.LocalDate;
import java.util.List;

@Service
public class GestorImportacionVinoService {

    @Autowired
    private BodegaRepository bodegaRepository;

    

    @Autowired
    private VinoApiService vinoApiService;
    // @Autowired
    // private EnofiloRepository enofiloRepository;


    public GestorImportacionVinoService(BodegaRepository bodegaRepository) {
        this.bodegaRepository = bodegaRepository;
    }

    public List<Bodega> importarActualizacionVinoBodega(){
        return buscarBodegasActDisponible();
    }

    public List<Bodega> buscarBodegasActDisponible() {
        LocalDate fechaActual = buscarFechaActual();
        List<Bodega> bodegas = bodegaRepository.findAll();
    
        return bodegas.stream()
            .filter(b -> b.tieneActualizacionDisponible(b.getFechaActualizacion(), fechaActual))
            .toList();
    }

    public List<Vino> obtenerActualizacionesBodegaSel(Bodega bodegaSeleccionada){
        return vinoApiService.obtenerActualizaciones();
    }

    public void actualizarVinosBodegaSel(Bodega bodegaSeleccionada, List<Vino> vinosActualizados){
        bodegaSeleccionada.actualizarDatosVinosBodega(bodegaSeleccionada, vinosActualizados);

    }
    

    public LocalDate buscarFechaActual() {
        return LocalDate.now();
    }

    public String tomarSelBodega(Bodega bodegaSeleccionada) {
        System.out.println("Bodega seleccionada: " + bodegaSeleccionada);
        return bodegaSeleccionada.getNombre();
    }

    public List<Vino> obtenerResumenVinosActualizados(Bodega bodega) {
        return bodegaRepository.findVinosByBodegaId(bodega.getId());
    }


}
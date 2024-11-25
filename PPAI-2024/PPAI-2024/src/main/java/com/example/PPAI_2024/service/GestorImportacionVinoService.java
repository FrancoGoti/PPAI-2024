package com.example.PPAI_2024.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.PPAI_2024.entity.Bodega;
import com.example.PPAI_2024.entity.Enofilo;
import com.example.PPAI_2024.entity.Usuario;
import com.example.PPAI_2024.entity.Vino;
import com.example.PPAI_2024.entity.VinoApiService;
import com.example.PPAI_2024.patron_observer.IObservadorNotificacionNovedades;
import com.example.PPAI_2024.patron_observer.InterfazNotificacionApp;
import com.example.PPAI_2024.repository.BodegaRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
public class GestorImportacionVinoService {

    @Autowired
    private BodegaRepository bodegaRepository;

    @Autowired
    private EnofiloService enofiloService;
    
    public InterfazNotificacionApp interfazNotificacionApp;

    @Autowired
    private VinoApiService vinoApiService;
    // @Autowired
    // private EnofiloRepository enofiloRepository;

    private List<Usuario> usuariosEnofilosSuscriptos = new ArrayList<>();

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

    public List<Usuario> buscarEnofilosSuscriptos(Bodega bodegaSeleccionada){
        List<Enofilo> enofilos = enofiloService.listarTodos();
        for (int i=0; i < enofilos.size(); i++){
            if(enofilos.get(i).esSeguidorBodega(bodegaSeleccionada) == true){
                usuariosEnofilosSuscriptos.add(enofilos.get(i).getUsuario());
            }
        }

        InterfazNotificacionApp interfazNotificacionApp = new InterfazNotificacionApp();
        suscribir(interfazNotificacionApp);
        notificar(bodegaSeleccionada);
        
        return usuariosEnofilosSuscriptos;
    }
      
    public void notificar(Bodega bodegaSeleccionada) {
       interfazNotificacionApp.actualizar(bodegaSeleccionada);
    }

    
    public void suscribir(IObservadorNotificacionNovedades observador) {
        this.interfazNotificacionApp = (InterfazNotificacionApp) observador;
    }

    
    public void quitar(IObservadorNotificacionNovedades observador) {
        
    }
  

}
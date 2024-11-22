package com.example.PPAI_2024.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.PPAI_2024.entity.Bodega;
import com.example.PPAI_2024.entity.Vino;
import com.example.PPAI_2024.service.GestorImportacionVinoService;
import java.util.List;
import com.example.PPAI_2024.repository.VinoRepository;


@RestController
@RequestMapping("/api/gestor")
public class GestorImportacionVinoController {

    @Autowired
    private GestorImportacionVinoService gestorService;

    @Autowired
    private VinoRepository vinoRepository;

    public GestorImportacionVinoController(GestorImportacionVinoService gestorService) {
        this.gestorService = gestorService;
    }
    
    /**
     * Endpoint para obtener bodegas con actualizaciones disponibles.
     */
     @GetMapping("/actualizaciones")
     public List<Bodega> buscarBodegasActDisponible() {
        //  LocalDate fechaActual = gestorService.buscarFechaActual();
         return gestorService.buscarBodegasActDisponible();
    }

     @PutMapping("/{bodegaId}/vinos")
     public void actualizarVinosBodega(@PathVariable Long bodegaId, @RequestBody List<Vino> nuevosVinos) {
         gestorService.actualizarVinosBodega(bodegaId, nuevosVinos);
     }

    //  @GetMapping("/{bodegaId}/enofilos")
    //  public void buscarEnofilosSuscriptos(@PathVariable Long bodegaId) {
    //      gestorService.buscarEnofilosSuscriptos(bodegaId);
    //  }

     /* Endpoint para seleccionar una bodega.*/
     @PostMapping("/seleccionar")
     public void tomarSelBodega(@RequestParam Bodega bodegaSeleccionada) {
        gestorService.tomarSelBodega(bodegaSeleccionada);
     }

    
      public List<Vino> obtenerResumenVinosActualizados(Long bodegaId) {
         return vinoRepository.findVinosByBodegaId(bodegaId);

}

}
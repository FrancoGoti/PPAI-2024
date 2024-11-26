package com.example.PPAI_2024.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.PPAI_2024.entity.Bodega;
import com.example.PPAI_2024.entity.Vino;
import com.example.PPAI_2024.service.BodegaService;
import com.example.PPAI_2024.service.GestorImportacionVinoService;
import com.example.PPAI_2024.service.VinoService;

import org.springframework.ui.Model;
import java.util.List;

@Controller
@RequestMapping("/pantalla-importar-vinos")
public class PantallaImportarVinos {

    @Autowired
    private BodegaService bodegaService;

    private final GestorImportacionVinoService gestor;

    @Autowired
    private VinoService vinoService;

    public PantallaImportarVinos(GestorImportacionVinoService gestor) {
        this.gestor = gestor;
    }

    // Método para mostrar la pantalla principal
    @GetMapping
    public String mostrarPantalla(Model model) {
        return "pantalla-importar-vinos"; // Nombre del archivo HTML
    }
   
    // Método para importar actualizaciones
    @GetMapping("/importar")
    public String opcionImportarActualizacionVinoBodega(Model model) {
        List<Bodega> bodegas = gestor.importarActualizacionVinoBodega();
        model.addAttribute("bodegas", bodegas);
        return "pantalla-importar-vinos";
    }

    // Método para seleccionar bodega
    // @GetMapping("/seleccionar-bodega")
    // public String tomarSeleccionBodega(@RequestParam("bodegaId") Long bodegaId, Model model) {
    //     //Se obtiene el objeto Bodega usando el id
    //     Bodega bodegaSeleccionada = bodegaService.obtenerPorId(bodegaId); // Este método recupera la Bodega por su ID
    //     gestor.tomarSelBodega(bodegaSeleccionada);
        
    //     //Se listan los vinos actualizados para mostrar en tabla
    //     List<Vino> vinosActualizados = gestor.obtenerActualizacionesBodegaSel(bodegaSeleccionada);
    //     gestor.actualizarVinosBodegaSel(bodegaSeleccionada, vinosActualizados);
    //     List<Vino> vinos = bodegaService.obtenerVinosPorBodega(bodegaSeleccionada);
    //     model.addAttribute("vinos", vinos);

    //     //Se comienza con el proceso de notificacion
    //     gestor.buscarEnofilosSuscriptos(bodegaSeleccionada);
        
    //     //Alerta para avisar que se ha notificado a los enofilos 
    //     System.out.println("Enofilos notificados: true");
    //     model.addAttribute("enofilosNotificados", true);
    //     return "pantalla-importar-vinos"; // Página que muestra los vinos actualizados

    // }

    @GetMapping("/seleccionar-bodega")
    public String tomarSeleccionBodega(@RequestParam("bodegaId") Long bodegaId, Model model) {
    // Se obtiene el objeto Bodega usando el id
    Bodega bodegaSeleccionada = bodegaService.obtenerPorId(bodegaId); // Este método recupera la Bodega por su ID
    
    gestor.tomarSelBodega(bodegaSeleccionada);
    
    // Se listan los vinos actualizados para mostrar en tabla
    List<Vino> vinosActualizados = gestor.obtenerActualizacionesBodegaSel(bodegaSeleccionada);
    
    // Actualizamos los vinos en memoria
    gestor.actualizarVinosBodegaSel(bodegaSeleccionada, vinosActualizados);
    
    // Guardamos los vinos actualizados en la base de datos
    for (Vino vino : bodegaSeleccionada.getVinosBodega()) {
        vinoService.actualizarVino(vino);  // Persistimos cada vino de la bodega
    }
    
    // Obtenemos los vinos actualizados de la base de datos (después de haber sido guardados)
    List<Vino> vinos = bodegaService.obtenerVinosPorBodega(bodegaSeleccionada);
    model.addAttribute("vinos", vinos);

    // Comienza el proceso de notificación
    gestor.buscarEnofilosSuscriptos(bodegaSeleccionada);
    
    // Alerta para avisar que se ha notificado a los enofilos 
    System.out.println("Enofilos notificados: true");
    model.addAttribute("enofilosNotificados", true);
    
    return "pantalla-importar-vinos"; // Página que muestra los vinos actualizados
}

}
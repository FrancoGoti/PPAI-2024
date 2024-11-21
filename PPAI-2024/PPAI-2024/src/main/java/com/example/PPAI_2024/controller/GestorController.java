import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/gestor")
public class GestorImportacionVinoController {

    @Autowired
    private GestorImportacionVinoService gestorService;

    public GestorImportacionVinoController(GestorImportacionVinoService gestorService) {
        this.gestorService = gestorService;
    }

    /**
     * Endpoint para obtener bodegas con actualizaciones disponibles.
     */
    @GetMapping("/actualizaciones")
    public List<String> buscarBodegasActDisponible() {
        Date fechaActual = gestorService.buscarFechaActual();
        return gestorService.buscarBodegasActDisponible(fechaActual);
    }

    @PutMapping("/{bodegaId}/vinos")
    public void actualizarVinosBodega(@PathVariable Long bodegaId, @RequestBody List<Vino> nuevosVinos) {
        gestorService.actualizarVinosBodega(bodegaId, nuevosVinos);
    }

    @GetMapping("/{bodegaId}/enofilos")
    public void buscarEnofilosSuscriptos(@PathVariable Long bodegaId) {
        gestorService.buscarEnofilosSuscriptos(bodegaId);
    }

    /* Endpoint para seleccionar una bodega.*/
    @PostMapping("/seleccionar")
    public void tomarSelBodega(@RequestParam String bodegaSeleccionada) {
        gestorService.tomarSelBodega(bodegaSeleccionada);
    }

}

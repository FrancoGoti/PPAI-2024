import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.PPAI_2024.entity.Bodega;
import com.example.PPAI_2024.entity.Enofilo;
import com.example.PPAI_2024.repository.BodegaRepository;
import com.example.PPAI_2024.repository.EnofiloRepository;
import com.example.PPAI_2024.repository.VinoRepository;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class GestorImportacionVinoService {

     @Autowired
    private BodegaRepository bodegaRepository;

    @Autowired
    private EnofiloRepository enofiloRepository;

    @Autowired
    private VinoRepository vinoRepository;

    public GestorImportacionVinoService(BodegaRepository bodegaRepository) {
        this.bodegaRepository = bodegaRepository;
    }

    /**
     * Buscar las bodegas con actualizaciones disponibles.
     */
    public List<Bodega> buscarBodegasActDisponible() {
        Date fechaActual = buscarFechaActual();
        List<Bodega> bodegas = bodegaRepository.findAll();

        List<Bodega> bodegasConActDisponible = bodegas.stream()
            .filter(b -> b.tieneActualizacionDisponible(
                b.getFechaActualizacion().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                fechaActual.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()))
            .toList();

        return bodegasConActDisponible;
    }

    public void actualizarVinosBodega(Long bodegaId, List<Vino> nuevosVinos) {
        Bodega bodega = bodegaRepository.findById(bodegaId)
            .orElseThrow(() -> new RuntimeException("Bodega no encontrada"));

        bodega.setVinos(nuevosVinos);
        bodegaRepository.save(bodega);
    }

    
    public void buscarEnofilosSuscriptos(Long bodegaId) {
        List<Enofilo> enofilos = enofiloRepository.findByBodegaId(bodegaId);
        // Lógica para notificar a los enófilos...
    }

    public Date buscarFechaActual() {
        return new Date();
    }

    /**
     * Setear la bodega seleccionada (caso de uso futuro).
     */
    public void tomarSelBodega(String bodegaSeleccionada) {
        // Lógica de selección podría ser almacenada o procesada aquí
        System.out.println("Bodega seleccionada: " + bodegaSeleccionada);
    }

    /**
     * Buscar la fecha actual.
     */
    public Date buscarFechaActual() {
        return new Date();
    }
}

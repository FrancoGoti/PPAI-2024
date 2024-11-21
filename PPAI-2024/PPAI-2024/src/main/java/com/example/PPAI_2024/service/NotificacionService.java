@Service
public class NotificacionService {

    public void enviarNotificacion(Bodega bodega, List<String> nombresUsuariosSuscriptos) {
        // Lógica para enviar la notificación (por ejemplo, correo electrónico)
        System.out.println("La bodega: " + bodega.getNombre() + " ha sido actualizada");
    }
}
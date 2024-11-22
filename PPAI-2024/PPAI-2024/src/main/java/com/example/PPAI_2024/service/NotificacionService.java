package com.example.PPAI_2024.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.PPAI_2024.entity.Bodega;

@Service
public class NotificacionService {

    public void enviarNotificacion(Bodega bodega, List<String> nombresUsuariosSuscriptos) {
        // Lógica para enviar la notificación (por ejemplo, correo electrónico)
        System.out.println("La bodega: " + bodega.getNombre() + " ha sido actualizada");
    }
}
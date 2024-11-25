package com.example.PPAI_2024.patron_observer;

import java.util.List;

import com.example.PPAI_2024.entity.Bodega;
import com.example.PPAI_2024.entity.Usuario;


public class InterfazNotificacionApp implements IObservadorNotificacionNovedades{
    

    public InterfazNotificacionApp(){
    };

    public void actualizar(Bodega bodegaActualizada, List<Usuario> enofilosSuscriptos){
        enviarNotificacion(bodegaActualizada, enofilosSuscriptos);
        
    };

    public String enviarNotificacion(Bodega bodegaActualizada, List<Usuario> enofilosSuscriptos){
        
        System.out.println("Se notifico la actualizacion a: ");
        for (int i=0; i < enofilosSuscriptos.size(); i++){
            System.out.println(enofilosSuscriptos.get(i).getNombre());
        }
        
        String mensaje = "Se ha actualizado la bodega " + bodegaActualizada.getNombre() + " a la cual esta suscripto";
        System.out.println(mensaje);
        return mensaje;
    };





}


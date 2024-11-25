package com.example.PPAI_2024.patron_observer;

import com.example.PPAI_2024.entity.Bodega;


public class InterfazNotificacionApp implements IObservadorNotificacionNovedades{
    

    public InterfazNotificacionApp(){
    };

    public void actualizar(Bodega bodegaActualizada){
        enviarNotificacion(bodegaActualizada);
    };

    public String enviarNotificacion(Bodega bodegaActualizada){
        String mensaje = "Se ha actualizado la bodega" + bodegaActualizada.getNombre() + " a la cual esta suscripto";
        System.out.println(mensaje);
        return mensaje;
    };





}


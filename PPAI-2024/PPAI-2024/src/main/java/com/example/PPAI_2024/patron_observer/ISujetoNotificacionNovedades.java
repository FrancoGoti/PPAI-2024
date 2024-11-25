package com.example.PPAI_2024.patron_observer;

public interface ISujetoNotificacionNovedades {
    public void suscribir(IObservadorNotificacionNovedades observador);
    public void quitar(IObservadorNotificacionNovedades observador);
    void notificar();
}

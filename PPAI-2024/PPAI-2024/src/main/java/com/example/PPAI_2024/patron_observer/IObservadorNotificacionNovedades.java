package com.example.PPAI_2024.patron_observer;

import java.util.List;

import com.example.PPAI_2024.entity.Bodega;
import com.example.PPAI_2024.entity.Usuario;

public interface IObservadorNotificacionNovedades {
    
    public void actualizar(Bodega bodega, List<Usuario> enofilos);

}

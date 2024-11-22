package com.example.PPAI_2024.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Siguiendo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Bodega bodega;

    public Siguiendo(Bodega bodega) {
        this.bodega = bodega;
    }

    public Bodega getBodega() {
        return this.bodega;
    }

    public void setBodega(Bodega bodega) {
        this.bodega = bodega;
    }

    public boolean sosBodega(Bodega bodegaSeleccionada) {
        return bodegaSeleccionada.equals(this.bodega);
    }
}

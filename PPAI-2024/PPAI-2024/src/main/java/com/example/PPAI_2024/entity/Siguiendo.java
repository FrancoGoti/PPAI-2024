package com.example.PPAI_2024.entity;

public class Siguiendo {
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

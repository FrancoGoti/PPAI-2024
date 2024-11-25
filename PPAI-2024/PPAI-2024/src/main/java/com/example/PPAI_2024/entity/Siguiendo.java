package com.example.PPAI_2024.entity;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Siguiendo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "bodega_id", referencedColumnName = "id")
    private Bodega bodega;

    public Siguiendo() {
    }
    
    public Long getId() {
        return id;
    }

    public Bodega getBodega() {
        return this.bodega;
    }

    public void setBodega(Bodega bodega) {
        this.bodega = bodega;
    }

    // public boolean sosDeBodegaSel(Bodega bodegaSeleccionada) {
    //     boolean condicion = false;
    //     if (bodegaSeleccionada.equals(this.bodega)) {
    //         condicion = true;
    //     }
    //     else{
    //         condicion = false;
    //     }
    //     return condicion;
    // }

    public boolean sosDeBodegaSel(Bodega bodegaSeleccionada) {
        return bodegaSeleccionada.equals(this.bodega);
    }

    @Override
    public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    Bodega bodega = (Bodega) obj;
    return Objects.equals(id, bodega.getId()); // Usa un atributo único
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }


}

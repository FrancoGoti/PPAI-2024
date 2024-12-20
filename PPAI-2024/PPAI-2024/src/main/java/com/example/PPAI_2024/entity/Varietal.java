package com.example.PPAI_2024.entity;
import java.util.Objects;

import jakarta.persistence.*;

@Entity
public class Varietal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST) 
    @JoinColumn(name = "tipo_uva_id", nullable = false) // Relación con TipoUva
    private TipoUva tipoUva;

    private String descripcion;
    private float porcentajeComposicion;

    // Constructor vacío requerido por JPA
    public Varietal() {}

    // Constructor completo
    public Varietal(String descripcion, float porcentajeComposicion, TipoUva tipoUva) {
        this.descripcion = descripcion;
        this.porcentajeComposicion = porcentajeComposicion;
        this.tipoUva = tipoUva;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public TipoUva getTipoUva() {
        return tipoUva;
    }

    public void setTipoUva(TipoUva tipoUva) {
        this.tipoUva = tipoUva;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getPorcentajeComposicion() {
        return porcentajeComposicion;
    }

    public void setPorcentajeComposicion(float porcentajeComposicion) {
        this.porcentajeComposicion = porcentajeComposicion;
    }

    @Override
    public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Varietal varietal = (Varietal) o;
    return Objects.equals(id, varietal.id);
    }
    
    @Override
    public int hashCode() {
    return Objects.hash(id);
    }

}

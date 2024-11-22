package com.example.PPAI_2024.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;


@Entity
public class Vino {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int añada;

    
    private LocalDate fechaActualizacion;

    private String nombre;

    @Column(length = 1000)
    private String notaDeCataBodega;

    private float precio;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "maridaje_id")
    private Maridaje maridaje;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "varietal_id")
    private Varietal varietal;

    @ManyToOne
    @JoinColumn(name = "bodega_id")
    private Bodega bodega;

    // Constructor vacío requerido por JPA
    public Vino() {
    }

    // Constructor completo
    public Vino(int añada, LocalDate fechaActualizacion, String nombre, String notaDeCataBodega, float precio, Maridaje maridaje, Varietal varietal) {
        this.añada = añada;
        this.fechaActualizacion = fechaActualizacion;
        this.nombre = nombre;
        this.notaDeCataBodega = notaDeCataBodega;
        this.precio = precio;
        this.maridaje = maridaje;
        this.varietal = varietal;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAñada() {
        return añada;
    }

    public void setAñada(int añada) {
        this.añada = añada;
    }

    public LocalDate getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(LocalDate fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNotaDeCataBodega() {
        return notaDeCataBodega;
    }

    public void setNotaDeCataBodega(String notaDeCataBodega) {
        this.notaDeCataBodega = notaDeCataBodega;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public Maridaje getMaridaje() {
        return maridaje;
    }

    public void setMaridaje(Maridaje maridaje) {
        this.maridaje = maridaje;
    }

    public Varietal getVarietal() {
        return varietal;
    }

    public void setVarietal(Varietal varietal) {
        this.varietal = varietal;
    }

    public Bodega getBodega() {
        return bodega;
    }

    public void setBodega(Bodega bodega) {
        this.bodega = bodega;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vino vino = (Vino) o;
        return Objects.equals(nombre, vino.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }
}

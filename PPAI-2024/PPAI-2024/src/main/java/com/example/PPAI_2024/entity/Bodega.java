package com.example.PPAI_2024.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Bodega implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String historia;
    
    private LocalDate fechaActualizacion;
    
    private int periodoActualizacion;

    private String coordenadas;

    private String descripcion;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bodega", orphanRemoval = true)
    private List<Vino> vinosBodega = new ArrayList<>();
    // @OneToMany(mappedBy = "bodega", orphanRemoval = true)

    // Constructor vacío requerido por JPA
    public Bodega() {
    }

    // Constructor completo
    public Bodega(String nombre, String historia, LocalDate fechaActualizacion, int periodoActualizacion, 
                  String coordenadas, String descripcion, List<Vino> vinosBodega) {
        this.nombre = nombre;
        this.historia = historia;
        this.fechaActualizacion = fechaActualizacion;
        this.periodoActualizacion = periodoActualizacion;
        this.coordenadas = coordenadas;
        this.descripcion = descripcion;
        this.vinosBodega = vinosBodega;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getHistoria() {
        return historia;
    }

    public void setHistoria(String historia) {
        this.historia = historia;
    }

    public LocalDate getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(LocalDate fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public int getPeriodoActualizacion() {
        return periodoActualizacion;
    }

    public void setPeriodoActualizacion(int periodoActualizacion) {
        this.periodoActualizacion = periodoActualizacion;
    }

    public String getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(String coordenadas) {
        this.coordenadas = coordenadas;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Vino> getVinosBodega() {
        return vinosBodega;
    }

    public void setVinosBodega(List<Vino> vinosBodega) {
        this.vinosBodega = vinosBodega;
    }

    @Override
    public String toString() {
        return nombre;
    }

    public boolean tieneActualizacionDisponible(LocalDate fechaActualizacion, LocalDate fechaActual) {
         long diferenciaFechas = ChronoUnit.MONTHS.between(fechaActualizacion, fechaActual);
        if (diferenciaFechas > periodoActualizacion){
            return true;
        }
        else{
            return false;
        }
    }
}
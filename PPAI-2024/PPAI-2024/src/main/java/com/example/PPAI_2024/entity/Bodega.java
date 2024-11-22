package com.example.PPAI_2024.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Bodega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Column(length = 500)
    private String historia;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActualizacion;

    private int periodoActualizacion;

    private String coordenadas;

    @Column(length = 1000)
    private String descripcion;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bodega", orphanRemoval = true)
    private List<Vino> vinosBodega = new ArrayList<>();

    // Constructor vacío requerido por JPA
    public Bodega() {
    }

    // Constructor completo
    public Bodega(String nombre, String historia, Date fechaActualizacion, int periodoActualizacion, 
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

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
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

    public Object tieneActualizacionDisponible(LocalDate localDate, LocalDate localDate2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'tieneActualizacionDisponible'");
    }
}

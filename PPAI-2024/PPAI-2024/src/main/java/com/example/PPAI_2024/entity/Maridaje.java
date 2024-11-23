package com.example.PPAI_2024.entity;

import java.util.Objects;

import jakarta.persistence.*;

@Entity
public class Maridaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String descripcion;

    // Constructor vacío requerido por JPA
    public Maridaje() {}

    // Constructor completo
    public Maridaje(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Maridaje maridaje = (Maridaje) o;
    return Objects.equals(id, maridaje.id);
    }
    
    @Override
    public int hashCode() {
    return Objects.hash(id);
}

}

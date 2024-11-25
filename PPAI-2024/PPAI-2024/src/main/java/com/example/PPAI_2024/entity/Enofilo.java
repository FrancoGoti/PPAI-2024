package com.example.PPAI_2024.entity;


import jakarta.persistence.*;
import java.util.Objects;

@Entity
public class Enofilo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellido;

    @ManyToOne
    private Siguiendo seguido;

    @OneToOne
    private Usuario usuario;

    @ManyToOne
    private Vino favorito;

    public Enofilo() {
    }


    public Enofilo(String nombre, String apellido, Siguiendo seguido, Usuario usuario) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.seguido = seguido;
        this.usuario = usuario;
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Siguiendo getSeguido() {
        return seguido;
    }

    public void setSeguido(Siguiendo seguido) {
        this.seguido = seguido;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Vino getFavorito() {
        return favorito;
    }

    public void setFavorito(Vino favorito) {
        this.favorito = favorito;
    }

    public boolean esSeguidorBodega(Bodega bodegaSeleccionada) {
        if (seguido != null) {
            if (seguido.sosDeBodegaSel(bodegaSeleccionada)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Enofilo enofilo = (Enofilo) o;
        return Objects.equals(id, enofilo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

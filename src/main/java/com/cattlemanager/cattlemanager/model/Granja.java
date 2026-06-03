package com.cattlemanager.cattlemanager.model;
import jakarta.persistence.*;
/*La entidad Granja representa las explotaciones ganaderas del sistema y mantiene
una relación muchos a uno con la entidad Usuario, ya que un usuario puede gestionar
varias granjas.*/
@Entity
public class Granja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String ubicacion;

    private String telefono;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Granja() {
    }

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

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}


package com.cattlemanager.cattlemanager.model;
// Importaciones de JPA para mapear la clase a una tabla
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
// Indica que esta clase es una entidad de base de datos (una tabla)
@Entity
public class Rol {
    // Clave primaria de la tabla
    @Id
    // El valor se genera automáticamente en la base de datos (autoincremento)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

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
    
}


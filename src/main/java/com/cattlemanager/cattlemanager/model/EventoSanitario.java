package com.cattlemanager.cattlemanager.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class EventoSanitario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipo;           // Ej: Vacuna, Desparasitación
    private String descripcion;
    private LocalDate fecha;

    @ManyToOne
    @JoinColumn(name = "animal_id")
    private Animal animal;

    public EventoSanitario() {
    }

    public EventoSanitario(String tipo, String descripcion, LocalDate fecha, Animal animal) {
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.animal = animal;
    }

    public Long getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }
}

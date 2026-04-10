package com.cattlemanager.cattlemanager.model;

import jakarta.persistence.*;
import java.time.LocalDate;

// Representa las tareas asignadas a los peones de una granja
@Entity
public class Tarea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String descripcion;
    private LocalDate fechaVencimiento;

    // false = pendiente, true = completada
    private boolean completada = false;

    @ManyToOne
    @JoinColumn(name = "granja_id")
    private Granja granja;

    public Tarea() {}

    public Long getId() { return id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public LocalDate getFechaVencimiento() { return fechaVencimiento; }
    public void setFechaVencimiento(LocalDate fechaVencimiento) { this.fechaVencimiento = fechaVencimiento; }

    public boolean isCompletada() { return completada; }
    public void setCompletada(boolean completada) { this.completada = completada; }

    public Granja getGranja() { return granja; }
    public void setGranja(Granja granja) { this.granja = granja; }
}

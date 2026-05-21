package cattlemanager.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import java.time.LocalDate;

// Representa las tareas asignadas a los peones de una granja
@Entity
public class Tarea extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String descripcion;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fechaVencimiento;

    // false = pendiente, true = completada
    private boolean completada = false;

    @ManyToOne
    @JoinColumn(name = "granja_id")
    private Granja granja;

    @ManyToOne
    @JoinColumn(name = "peon_id")
    private Usuario peon;

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

    public Usuario getPeon() { return peon; }
    public void setPeon(Usuario peon) { this.peon = peon; }
}

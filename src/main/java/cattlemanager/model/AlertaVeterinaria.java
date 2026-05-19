package cattlemanager.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import java.time.LocalDate;

// Alerta que encargado o peón envían al veterinario sobre un problema en un animal
@Entity
public class AlertaVeterinaria extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descripcion;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fecha;

    // false = pendiente (no vista por el vet), true = atendida
    private boolean atendida = false;

    @ManyToOne
    @JoinColumn(name = "animal_id")
    private Animal animal;

    // Encargado o peón que genera la alerta
    @ManyToOne
    @JoinColumn(name = "creado_por_id")
    private Usuario creadoPor;

    public AlertaVeterinaria() {}

    public Long getId() { return id; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public boolean isAtendida() { return atendida; }
    public void setAtendida(boolean atendida) { this.atendida = atendida; }

    public Animal getAnimal() { return animal; }
    public void setAnimal(Animal animal) { this.animal = animal; }

    public Usuario getCreadoPor() { return creadoPor; }
    public void setCreadoPor(Usuario creadoPor) { this.creadoPor = creadoPor; }
}

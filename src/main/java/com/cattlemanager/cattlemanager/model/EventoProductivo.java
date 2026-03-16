package com.cattlemanager.cattlemanager.model;
// Importaciones necesarias
import jakarta.persistence.*;// Librería JPA para mapear clases a tablas de base de datos
import java.time.LocalDate;// Clase para manejar fechas (año-mes-día)
/*Esta clase representa un evento productivo de un animal
  (por ejemplo: parto, vacunación, venta, enfermedad, etc).
  La anotación @Entity indica que esta clase será una tabla en la base de datos.
 */
@Entity
public class EventoProductivo {
    /*
     Identificador único del evento.
     @Id -> indica que es la clave primaria.
     @GeneratedValue -> la base de datos genera el valor automáticamente.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // Tipo de evento (Ej: "Parto", "Vacunación", "Venta")
    private String tipo;
    // Descripción más detallada del evento
    private String descripcion;
    // Fecha en la que ocurrió el evento
    private LocalDate fecha;
    
    //Muchos eventos pueden pertenecer a un solo animal.
     
    @ManyToOne//relación muchos a uno
    @JoinColumn(name = "animal_id")//nombre de la columna que guardará el ID del animal en la tabla.
    private Animal animal;
    //Constructor vacío obligatorio para JPA.El framework lo usa para crear objetos automáticamente.
    public EventoProductivo() {
    }
    // Getter del ID (no suele tener setter porque se genera automáticamente)
    public Long getId() {
        return id;
    }
    // Getter y Setter del tipo de evento
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


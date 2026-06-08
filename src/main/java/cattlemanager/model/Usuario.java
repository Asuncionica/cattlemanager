package cattlemanager.model;
// Import para controlar cómo se convierte un objeto Java a JSON
import com.fasterxml.jackson.annotation.JsonProperty;
// Imports de JPA (Java Persistence API) para mapear la clase con la base de datos
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
//@Entity indica que esta clase representa una entidad que será almacenada en 
//una tabla de la base de datos.
@Entity
//Indica que esta entidad se corresponde con la tabla llamada "usuario".
@Table(name = "usuario")
//La clase Usuario hereda de Auditable, por lo que también dispone de los campos
//de auditoría (por ejemplo fecha de creación y modificación si están definidos allí).
public class Usuario extends Auditable {
    //Clave primaria de la tabla. Cada usuario tendrá un identificador único.
    @Id
    //El valor del id será generado automáticamente por la base de datos 
    //utilizando un campo autoincremental.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //Nombre del usuario.
    private String nombre;
    //Email del usuario.unique = true impide que existan dos usuarios con el
    //mismo email, evitando duplicados en el login.
    @Column(unique = true)
    private String email;
    //Contraseña del usuario. WRITE_ONLY significa que: - Se puede recibir desde
    //una petición (POST, PUT...). - No se enviará cuando el objeto se convierta
    //a JSON.De esta forma se mejora la seguridad y no se expone la contraseña
    //en las respuestas de la API.
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    //Relación Muchos a Uno (ManyToOne).Muchos usuarios pueden compartir el 
    //mismo rol(por ejemplo varios usuarios pueden ser PEÓN).
    @ManyToOne
    //La relación con la tabla Rol se realiza mediante la columna "rol_id", 
    //que actúa como clave foránea.
    @JoinColumn(name = "rol_id")
    private Rol rol;
    //Devuelve el identificador del usuario.
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    //Devuelve el nombre del usuario.
    public String getNombre() {
        return nombre;
    }
    //Modifica el nombre del usuario.
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    //Devuelve la contraseña del usuario.
    public String getPassword() {
        return password;
    }
    //Modifica la contraseña del usuario.
    public void setPassword(String password) {
        this.password = password;
    }
    //Devuelve el rol asociado al usuario.
    public Rol getRol() {
        return rol;
    }
    //Asigna un rol al usuario.
    public void setRol(Rol rol) {
        this.rol = rol;
    }
    
}

package com.cattlemanager.cattlemanager.model;
/*jakarta.persistence Es la librería que usa Spring Boot con JPA para conectar
tu código con la base de datos.
Sirven para decirle a Java:
“Esta clase y estos campos se guardan en una base de datos, y así es como deben
guardarse”*/
import jakarta.persistence.Column;//configuar columnas.
import jakarta.persistence.Entity;//Convertir clase en tabla.
import jakarta.persistence.GeneratedValue;//Id automático
import jakarta.persistence.GenerationType;//Cómo se genera el Id.
import jakarta.persistence.Id;//Clave primaria
import jakarta.persistence.JoinColumn;//Columna de relación
import jakarta.persistence.ManyToOne;//Relación muchos a uno
import jakarta.persistence.Table;//Nombrar la tabla

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    
    @Column(unique = true)//Evita que dos usuarios tengan el mismo login
    private String email;

    private String password;

    @ManyToOne
    @JoinColumn(name = "rol_id")
    private Rol rol;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
    
}


package com.cattlemanager.cattlemanager.model;
import jakarta.persistence.*;
/*Esta clase representa un ANIMAL en la base de datos.
 * 👉 Es una "entidad", es decir, una tabla en la base de datos.*/
@Entity
public class Animal {
    /*ID único del animal.
     * @Id → indica que es la clave primaria
     * @GeneratedValue → el ID se genera automáticamente. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /*Identificador del animal (ej: código o número).*/
    private String identificador;
    /*Raza del animal (ej: Holstein, Angus...).*/
    private String raza;
    /* Sexo del animal (Macho / Hembra).*/
    private String sexo;
    /* Fecha de nacimiento del animal
     * (⚠️ Aquí está como String, aunque normalmente sería tipo Date o LocalDate)
     */
    private String fechaNacimiento;
    /* Relación con la entidad Granja
     * @ManyToOne → muchos animales pueden pertenecer a una granja
     * @JoinColumn → crea la columna "granja_id" en la base de datos
     */
    @ManyToOne
    @JoinColumn(name = "granja_id")
    private Granja granja;
    /*Constructor vacío (OBLIGATORIO para JPA).*/
    public Animal() {
    }
    /* Constructor con parámetros
     * 👉 Sirve para crear objetos Animal fácilmente.*/
    public Animal(String identificador, String raza, String sexo, String fechaNacimiento, Granja granja) {
        this.identificador = identificador;
        this.raza = raza;
        this.sexo = sexo;
        this.fechaNacimiento = fechaNacimiento;
        this.granja = granja;
    }
    /* Getter del ID (no tiene setter porque normalmente no se modifica).*/
    public Long getId() {
        return id;
    }
    /* Getter y Setter de identificador. */
    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Granja getGranja() {
        return granja;
    }

    public void setGranja(Granja granja) {
        this.granja = granja;
    }
}

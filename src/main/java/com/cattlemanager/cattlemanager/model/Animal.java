package com.cattlemanager.cattlemanager.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entidad que representa un Animal dentro del sistema.
 *
 * <p>Esta clase se mapea a una tabla en la base de datos mediante JPA.
 * Cada instancia de Animal corresponde a un registro en dicha tabla.</p>
 *
 * <p>Un animal contiene información básica como identificador, raza,
 * sexo y fecha de nacimiento, además de su relación con una granja.</p>
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Animal {

    /**
     * Identificador único del animal.
     *
     * <p>Es la clave primaria de la entidad.</p>
     * <ul>
     *   <li>@Id → Indica que es la clave primaria</li>
     *   <li>@GeneratedValue → El valor se genera automáticamente</li>
     *   <li>Strategy IDENTITY → La base de datos autoincrementa el ID</li>
     * </ul>
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Código o identificador del animal.
     *
     * <p>Ejemplo: "VACA001", "TORO12"</p>
     */
    private String identificador;

    /**
     * Raza del animal.
     *
     * <p>Ejemplos: Holstein, Angus, Jersey.</p>
     */
    private String raza;

    /**
     * Sexo del animal.
     *
     * <p>Valores comunes: "Macho", "Hembra"</p>
     */
    private String sexo;

    /**
     * Fecha de nacimiento del animal.
     *
     * <p>Actualmente almacenada como String, aunque se recomienda usar
     * tipos de fecha como {@link java.time.LocalDate} para mayor precisión
     * y control.</p>
     */
    private String fechaNacimiento;

    /**
     * Relación con la entidad Granja.
     *
     * <p>Define que muchos animales pueden pertenecer a una misma granja.</p>
     *
     * <ul>
     *   <li>@ManyToOne → Relación muchos a uno</li>
     *   <li>@JoinColumn → Define la columna "granja_id" en la tabla</li>
     * </ul>
     *
     * <p>En la base de datos, esto se traduce en una clave foránea
     * que apunta a la tabla de Granja.</p>
     */
    @ManyToOne
    @JoinColumn(name = "granja_id")
    private Granja granja;
}
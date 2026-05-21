package cattlemanager.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
public class Animal extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String identificador;
    private String raza;
    private String sexo;
    private String fechaNacimiento;

    @ManyToOne
    @JoinColumn(name = "granja_id")
    private Granja granja;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lote_genetico_id")
    @JsonIgnoreProperties("animales")
    private LoteGenetico loteGenetico;

    public Animal() {
    }

    public Animal(String identificador, String raza, String sexo, String fechaNacimiento, Granja granja) {
        this.identificador = identificador;
        this.raza = raza;
        this.sexo = sexo;
        this.fechaNacimiento = fechaNacimiento;
        this.granja = granja;
    }

    public Long getId() {
        return id;
    }

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

    public LoteGenetico getLoteGenetico() {
        return loteGenetico;
    }

    public void setLoteGenetico(LoteGenetico loteGenetico) {
        this.loteGenetico = loteGenetico;
    }
}

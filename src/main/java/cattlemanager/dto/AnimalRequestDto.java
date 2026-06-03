package cattlemanager.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class AnimalRequestDto {

    @NotBlank(message = "El identificador es obligatorio")
    private String identificador;

    @NotBlank(message = "La raza es obligatoria")
    private String raza;

    @NotBlank(message = "El sexo es obligatorio")
    @Pattern(regexp = "^(MACHO|HEMBRA)$", message = "El sexo debe ser MACHO o HEMBRA")
    private String sexo;

    private String fechaNacimiento;

    @NotNull(message = "La granja es obligatoria")
    private IdRef granja;

    private IdRef loteGenetico;
    private Long loteGeneticoId;

    public String getIdentificador() { return identificador; }
    public void setIdentificador(String identificador) { this.identificador = identificador; }

    public String getRaza() { return raza; }
    public void setRaza(String raza) { this.raza = raza; }

    public String getSexo() { return sexo; }
    public void setSexo(String sexo) { this.sexo = sexo; }

    public String getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(String fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    public IdRef getGranja() { return granja; }
    public void setGranja(IdRef granja) { this.granja = granja; }

    public IdRef getLoteGenetico() { return loteGenetico; }
    public void setLoteGenetico(IdRef loteGenetico) { this.loteGenetico = loteGenetico; }

    public Long getLoteGeneticoId() { return loteGeneticoId; }
    public void setLoteGeneticoId(Long loteGeneticoId) { this.loteGeneticoId = loteGeneticoId; }

    public Long getGranjaId() {
        return granja != null ? granja.getId() : null;
    }

    public Long getLoteId() {
        if (loteGeneticoId != null) return loteGeneticoId;
        return loteGenetico != null ? loteGenetico.getId() : null;
    }

    public static class IdRef {
        private Long id;
        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
    }
}

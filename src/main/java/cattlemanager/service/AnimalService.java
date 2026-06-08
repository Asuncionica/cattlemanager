package cattlemanager.service;

import cattlemanager.dto.AnimalRequestDto;
import cattlemanager.model.Animal;
import cattlemanager.model.Granja;
import cattlemanager.model.LoteGenetico;
import cattlemanager.repository.AnimalRepository;
import cattlemanager.repository.GranjaRepository;
import cattlemanager.repository.LoteGeneticoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalService {

    private final AnimalRepository animalRepository;
    private final GranjaRepository granjaRepository;
    private final LoteGeneticoRepository loteGeneticoRepository;

    public AnimalService(AnimalRepository animalRepository,
                         GranjaRepository granjaRepository,
                         LoteGeneticoRepository loteGeneticoRepository) {
        this.animalRepository = animalRepository;
        this.granjaRepository = granjaRepository;
        this.loteGeneticoRepository = loteGeneticoRepository;
    }

    public List<Animal> obtenerAnimales() {
        return animalRepository.findAll();
    }

    public Animal obtenerAnimalPorId(Long id) {
        return animalRepository.findById(id).orElse(null);
    }

    public Animal guardarAnimal(AnimalRequestDto animalRequest) {
        Animal animal = new Animal();
        aplicarDatos(animal, animalRequest);
        return animalRepository.save(animal);
    }

    public void eliminarAnimal(Long id) {
        animalRepository.deleteById(id);
    }

    public Animal actualizarAnimal(Long id, AnimalRequestDto animalActualizado) {
        Animal animal = animalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Animal no encontrado con id: " + id));

        aplicarDatos(animal, animalActualizado);

        return animalRepository.save(animal);
    }

    private void aplicarDatos(Animal animal, AnimalRequestDto animalRequest) {
        animal.setIdentificador(animalRequest.getIdentificador());
        animal.setRaza(animalRequest.getRaza());
        animal.setSexo(animalRequest.getSexo() != null ? animalRequest.getSexo().toUpperCase() : null);
        animal.setFechaNacimiento(animalRequest.getFechaNacimiento());

        Granja granja = resolverGranja(animalRequest.getGranjaId());
        animal.setGranja(granja);

        LoteGenetico loteGenetico = resolverLoteGenetico(animalRequest.getLoteId());
        animal.setLoteGenetico(loteGenetico);
    }

    private Granja resolverGranja(Long granjaId) {
        if (granjaId == null) {
            throw new RuntimeException("La granja es obligatoria");
        }

        return granjaRepository.findById(granjaId)
                .orElseThrow(() -> new RuntimeException("Granja no encontrada con id: " + granjaId));
    }

    private LoteGenetico resolverLoteGenetico(Long loteId) {
        if (loteId == null) {
            return null;
        }

        return loteGeneticoRepository.findById(loteId)
                .orElseThrow(() -> new RuntimeException("Lote genético no encontrado con id: " + loteId));
    }
}
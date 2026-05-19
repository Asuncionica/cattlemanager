package cattlemanager.service;

import cattlemanager.model.Animal;
import cattlemanager.repository.AnimalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalService {

    private final AnimalRepository animalRepository;

    public AnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    public List<Animal> obtenerAnimales() {
        return animalRepository.findAll();
    }

    public Animal obtenerAnimalPorId(Long id) {
        return animalRepository.findById(id).orElse(null);
    }

    public Animal guardarAnimal(Animal animal) {
        return animalRepository.save(animal);
    }

    public void eliminarAnimal(Long id) {
        animalRepository.deleteById(id);
    }

    public Animal actualizarAnimal(Long id, Animal animalActualizado) {
        Animal animal = animalRepository.findById(id).orElseThrow();
        animal.setIdentificador(animalActualizado.getIdentificador());
        animal.setRaza(animalActualizado.getRaza());
        animal.setSexo(animalActualizado.getSexo());
        animal.setFechaNacimiento(animalActualizado.getFechaNacimiento());
        animal.setGranja(animalActualizado.getGranja());
        return animalRepository.save(animal);
    }
}

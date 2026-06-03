package cattlemanager.controller;

import cattlemanager.dto.AnimalRequestDto;
import cattlemanager.model.Animal;
import cattlemanager.service.AnimalService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/animales")
public class AnimalController {

    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping
    public List<Animal> obtenerAnimales() {
        return animalService.obtenerAnimales();
    }

    @GetMapping("/{id}")
    public Animal obtenerAnimalPorId(@PathVariable Long id) {
        return animalService.obtenerAnimalPorId(id);
    }

    @PostMapping
    public Animal crearAnimal(@Valid @RequestBody AnimalRequestDto animal) {
        return animalService.guardarAnimal(animal);
    }

    @DeleteMapping("/{id}")
    public void eliminarAnimal(@PathVariable Long id) {
        animalService.eliminarAnimal(id);
    }

    @PutMapping("/{id}")
    public Animal actualizarAnimal(@PathVariable Long id, @Valid @RequestBody AnimalRequestDto animal) {
        return animalService.actualizarAnimal(id, animal);
    }
}

package com.cattlemanager.cattlemanager.controller;

import com.cattlemanager.cattlemanager.model.Animal;
import com.cattlemanager.cattlemanager.service.AnimalService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/animales")
public class AnimalController {

    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping
    public Page<Animal> obtenerAnimales(@PageableDefault(size = 20) Pageable pageable) {
        return animalService.obtenerAnimales(pageable);
    }

    // Carga los animales de una granja: sustituye el id hardcodeado en Android
    @GetMapping("/granja/{granjaId}")
    public Page<Animal> obtenerPorGranja(@PathVariable Long granjaId,
                                         @PageableDefault(size = 20) Pageable pageable) {
        return animalService.obtenerPorGranja(granjaId, pageable);
    }

    @PostMapping
    public Animal crearAnimal(@RequestBody Animal animal) {
        return animalService.guardarAnimal(animal);
    }

    @DeleteMapping("/{id}")
    public void eliminarAnimal(@PathVariable Long id) {
        animalService.eliminarAnimal(id);
    }

    @PutMapping("/{id}")
    public Animal actualizarAnimal(@PathVariable Long id, @RequestBody Animal animal) {
        return animalService.actualizarAnimal(id, animal);
    }
}

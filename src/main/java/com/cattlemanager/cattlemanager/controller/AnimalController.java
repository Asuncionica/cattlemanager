package com.cattlemanager.cattlemanager.controller;

import com.cattlemanager.cattlemanager.model.Animal;
import com.cattlemanager.cattlemanager.service.AnimalService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * Controlador REST para manejar operaciones sobre animales.
 * 
 * Este controlador expone endpoints para crear, leer, actualizar
 * y eliminar registros de animales en la aplicación de gestión de ganado.
 */
@RestController
@RequestMapping("/animales")// Define la ruta base para todos los endpoints de este controlador
public class AnimalController {
    // Servicio que maneja la lógica de negocio para los animales
    private final AnimalService animalService;
    /**
     * Constructor del controlador.
     * @param animalService Servicio de animales inyectado por Spring.
     */
    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }
    /**
     * Endpoint para obtener la lista de todos los animales.
     * HTTP GET /animales
     * 
     * @return Lista de objetos Animal.
     */
    @GetMapping
    public List<Animal> obtenerAnimales() {
        return animalService.obtenerAnimales();
    }
    /**
     * Endpoint para crear un nuevo animal.
     * HTTP POST /animales
     * 
     * @param animal Objeto Animal enviado en el cuerpo de la petición.
     * @return El animal recién creado.
     */
    @PostMapping
    public Animal crearAnimal(@RequestBody Animal animal) {
        return animalService.guardarAnimal(animal);
    }
    /**
     * Endpoint para eliminar un animal por su ID.
     * HTTP DELETE /animales/{id}
     * 
     * @param id Identificador del animal a eliminar.
     */
    @DeleteMapping("/{id}")
    public void eliminarAnimal(@PathVariable Long id) {
        animalService.eliminarAnimal(id);
    }
    /**
     * Endpoint para actualizar los datos de un animal existente.
     * HTTP PUT /animales/{id}
     * 
     * @param id Identificador del animal a actualizar.
     * @param animal Objeto Animal con los nuevos datos.
     * @return El animal actualizado.
     */
    @PutMapping("/{id}")
    public Animal actualizarAnimal(@PathVariable Long id,
                                   @RequestBody Animal animal) {
        return animalService.actualizarAnimal(id, animal);
    }
}


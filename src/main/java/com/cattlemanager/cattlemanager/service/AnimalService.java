package com.cattlemanager.cattlemanager.service;

import com.cattlemanager.cattlemanager.model.Animal;//Modelo que representa un 
//animal en la aplicación.
import com.cattlemanager.cattlemanager.repository.AnimalRepository;//Interfaz 
//que maneja la persistencia de los animales en la base de datos.
import org.springframework.stereotype.Service;//Anotación de Spring que marca 
//la clase como un servicio, parte de la lógica de negocio.

import java.util.List;//Colección de Java para manejar listas de objetos.

@Service
public class AnimalService {
    //Esta clase actúa como capa de servicio para manejar operaciones relacionadas
    //con los animales. Interactúa con el AnimalRepository para realizar 
    //CRUD (Crear, Leer, Actualizar, Eliminar).

    private final AnimalRepository animalRepository;
    //Repositorio de animales inyectado en el servicio.final indica que se 
    //inicializa una sola vez, garantizando inmutabilidad de la referencia.

    public AnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }//Constructor que permite la inyección de dependencias de Spring.
    //Permite que Spring cree el servicio y le pase automáticamente el repositorio.

    public List<Animal> obtenerAnimales() {
        return animalRepository.findAll();
        //Retorna una lista de todos los animales almacenados en la base de datos.
        //Usa findAll() de JpaRepository.
    }
    public Animal obtenerAnimalPorId(Long id) {
        return animalRepository.findById(id).orElse(null);
    }

    public Animal guardarAnimal(Animal animal) {
        return animalRepository.save(animal);
        //Guarda un nuevo animal en la base de datos.
        //Retorna el animal guardado con su ID generado (si aplica).
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
        //Actualiza un animal existente con nuevos datos.
        //findById(id).orElseThrow() → busca el animal por ID y lanza excepción si no existe
        //Se actualizan todos los campos relevantes (identificador, raza, sexo, fechaNacimiento, granja).
        //Guarda y retorna el animal actualizado.
    }
}
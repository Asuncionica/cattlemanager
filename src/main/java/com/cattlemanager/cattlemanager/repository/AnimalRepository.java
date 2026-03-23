package com.cattlemanager.cattlemanager.repository;
// Importa la clase Animal, que es la entidad de la base de datos que vamos a manejar.
import com.cattlemanager.cattlemanager.model.Animal;
// Importa JpaRepository, que es la interfaz de Spring Data JPA para operaciones CRUD.
import org.springframework.data.jpa.repository.JpaRepository;
/* Interfaz AnimalRepository.
 * Esta interfaz extiende JpaRepository para proporcionar operaciones CRUD
 * y consultas personalizadas (si se agregan) para la entidad Animal.
 * @param Animal La entidad que vamos a manejar.
 * @param Long   El tipo de dato del identificador (ID) de la entidad Animal.*/
public interface AnimalRepository extends JpaRepository<Animal, Long> {
    /* Actualmente no hay métodos adicionales.
     JpaRepository ya proporciona métodos como:
     - save(Animal animal) -> Guardar o actualizar un animal.
     - findById(Long id) -> Buscar un animal por su ID.
     - findAll() -> Obtener todos los animales.
     - deleteById(Long id) -> Eliminar un animal por su ID.
     - count() -> Contar la cantidad de animales.
    
    Si se desean consultas personalizadas, se pueden agregar métodos aquí
    usando la convención de nombres de Spring Data JPA, por ejemplo:
    List<Animal> findByNombre(String nombre);*/
}

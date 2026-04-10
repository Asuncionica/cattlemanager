package com.cattlemanager.cattlemanager.service;

import com.cattlemanager.cattlemanager.model.Animal;
import com.cattlemanager.cattlemanager.repository.AnimalRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AnimalService {

    private final AnimalRepository animalRepository;

    public AnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    public Page<Animal> obtenerAnimales(Pageable pageable) {
        return animalRepository.findAll(pageable);
    }

    // Filtra por granja; reemplaza el hardcode de granja_id=1 en Android
    public Page<Animal> obtenerPorGranja(Long granjaId, Pageable pageable) {
        return animalRepository.findByGranjaId(granjaId, pageable);
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

package com.cattlemanager.cattlemanager.repository;

import com.cattlemanager.cattlemanager.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository extends JpaRepository<Animal, Long> {

}

package com.cattlemanager.cattlemanager.repository;

import com.cattlemanager.cattlemanager.model.EventoReproductivo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoReproductivoRepository extends JpaRepository<EventoReproductivo, Long> {
    // Recupera el historial reproductivo de un animal concreto
    Page<EventoReproductivo> findByAnimalId(Long animalId, Pageable pageable);
}

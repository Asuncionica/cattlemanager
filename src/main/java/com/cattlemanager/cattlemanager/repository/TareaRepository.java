package com.cattlemanager.cattlemanager.repository;

import com.cattlemanager.cattlemanager.model.Tarea;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TareaRepository extends JpaRepository<Tarea, Long> {
    // Tareas de una granja, opcionalmente filtradas por estado
    Page<Tarea> findByGranjaId(Long granjaId, Pageable pageable);
    Page<Tarea> findByGranjaIdAndCompletada(Long granjaId, boolean completada, Pageable pageable);
}

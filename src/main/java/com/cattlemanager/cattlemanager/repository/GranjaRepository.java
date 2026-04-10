package com.cattlemanager.cattlemanager.repository;

import com.cattlemanager.cattlemanager.model.Granja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GranjaRepository extends JpaRepository<Granja, Long> {
    // Permite obtener solo las granjas del usuario autenticado
    List<Granja> findByUsuarioId(Long usuarioId);
}

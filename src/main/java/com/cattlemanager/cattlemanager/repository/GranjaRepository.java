package com.cattlemanager.cattlemanager.repository;

import com.cattlemanager.cattlemanager.model.Granja;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GranjaRepository extends JpaRepository<Granja, Long> {
    List<Granja> findByUsuarioId(Long usuarioId);

    Optional<Granja> findByIdAndUsuarioId(Long id, Long usuarioId);
}

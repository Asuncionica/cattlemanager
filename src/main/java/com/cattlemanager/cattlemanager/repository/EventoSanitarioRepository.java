package com.cattlemanager.cattlemanager.repository;

import com.cattlemanager.cattlemanager.model.EventoSanitario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoSanitarioRepository extends JpaRepository<EventoSanitario, Long> {

}


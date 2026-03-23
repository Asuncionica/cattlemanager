package com.cattlemanager.cattlemanager.repository;

import com.cattlemanager.cattlemanager.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;//JpaRepository ya 
//hace el trabajo de @Repository automáticamente
import org.springframework.stereotype.Repository;//Se añade si se quiere ser más
//explicito, pero no hace falta.

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {
}


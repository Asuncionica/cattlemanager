package com.cattlemanager.cattlemanager.service;

import com.cattlemanager.cattlemanager.model.Rol;
import com.cattlemanager.cattlemanager.repository.RolRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RolService {

    private final RolRepository rolRepository;

    public RolService(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    public Page<Rol> obtenerRoles(Pageable pageable) {
        return rolRepository.findAll(pageable);
    }

    public Rol obtenerPorId(Long id) {
        return rolRepository.findById(id).orElse(null);
    }

    public Rol guardar(Rol rol) {
        return rolRepository.save(rol);
    }

    public void eliminar(Long id) {
        rolRepository.deleteById(id);
    }

    public Rol actualizar(Long id, Rol rol) {
        rol.setId(id);
        return rolRepository.save(rol);
    }
}

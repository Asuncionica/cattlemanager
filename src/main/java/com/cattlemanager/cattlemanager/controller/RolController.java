package com.cattlemanager.cattlemanager.controller;

import com.cattlemanager.cattlemanager.model.Rol;
import com.cattlemanager.cattlemanager.repository.RolRepository;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roles")
public class RolController {

    private final RolRepository rolRepository;

    public RolController(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
        
    }

    @PostMapping
    public Rol crear(@RequestBody Rol rol){
        return rolRepository.save(rol);
    }

    @GetMapping
    public List<Rol> listar(){
        return rolRepository.findAll();
    }
    @GetMapping("/{id}")
    public Rol obtener(@PathVariable Long id){
        return rolRepository.findById(id).orElse(null);
    }
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id){
        rolRepository.deleteById(id);
    }
}


package com.cattlemanager.cattlemanager.controller;

import com.cattlemanager.cattlemanager.model.Rol;
import com.cattlemanager.cattlemanager.service.RolService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RolController {

    private final RolService rolService;

    public RolController(RolService rolService) {
        this.rolService = rolService;
    }

    @GetMapping
    public List<Rol> listar() {
        return rolService.obtenerRoles();
    }

    @GetMapping("/{id}")
    public Rol obtenerPorId(@PathVariable Long id){
        return rolService.obtenerPorId(id);
    }

    @PostMapping
    public Rol crear(@RequestBody Rol rol){
        return rolService.guardar(rol);
    }

    @PutMapping("/{id}")
    public Rol actualizar(@PathVariable Long id, @RequestBody Rol rol){
        return rolService.actualizar(id, rol);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id){
        rolService.eliminar(id);
    }
}

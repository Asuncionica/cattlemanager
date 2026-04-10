package com.cattlemanager.cattlemanager.controller;

import com.cattlemanager.cattlemanager.model.Granja;
import com.cattlemanager.cattlemanager.service.GranjaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/granjas")
public class GranjaController {

    private final GranjaService grService;

    public GranjaController(GranjaService grService) {
        this.grService = grService;
    }

    @GetMapping
    public Page<Granja> listar(@PageableDefault(size = 20) Pageable pageable) {
        return grService.obtenerGranjas(pageable);
    }

    // Endpoint clave para la app Android: carga las granjas del usuario logueado
    // evitando así tener que hardcodear granja_id=1
    @GetMapping("/usuario/{usuarioId}")
    public List<Granja> listarPorUsuario(@PathVariable Long usuarioId) {
        return grService.obtenerPorUsuario(usuarioId);
    }

    @PostMapping
    public Granja crear(@RequestBody Granja gr) {
        return grService.guardar(gr);
    }

    @PutMapping("/{id}")
    public Granja actualizar(@PathVariable Long id, @RequestBody Granja gr) {
        return grService.actualizar(id, gr);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        grService.eliminar(id);
    }
}

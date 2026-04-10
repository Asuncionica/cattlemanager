package com.cattlemanager.cattlemanager.controller;

import com.cattlemanager.cattlemanager.model.EventoSanitario;
import com.cattlemanager.cattlemanager.service.EventoSanitarioService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/eventos-sanitarios")
public class EventoSanitarioController {

    private final EventoSanitarioService service;

    public EventoSanitarioController(EventoSanitarioService service) {
        this.service = service;
    }

    @GetMapping
    public Page<EventoSanitario> listar(@PageableDefault(size = 20) Pageable pageable) {
        return service.obtenerEventos(pageable);
    }

    // Historial sanitario (vacunas, desparasitaciones…) de un animal concreto
    @GetMapping("/animal/{animalId}")
    public Page<EventoSanitario> listarPorAnimal(@PathVariable Long animalId,
                                                 @PageableDefault(size = 20) Pageable pageable) {
        return service.obtenerPorAnimal(animalId, pageable);
    }

    @PostMapping
    public EventoSanitario crear(@RequestBody EventoSanitario evento) {
        return service.guardarEvento(evento);
    }

    @PutMapping("/{id}")
    public EventoSanitario actualizar(@PathVariable Long id, @RequestBody EventoSanitario evento) {
        return service.actualizarEvento(id, evento);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminarEvento(id);
    }
}

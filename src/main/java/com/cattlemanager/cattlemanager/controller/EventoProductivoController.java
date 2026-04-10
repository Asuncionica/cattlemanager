package com.cattlemanager.cattlemanager.controller;

import com.cattlemanager.cattlemanager.model.EventoProductivo;
import com.cattlemanager.cattlemanager.service.EventoProductivoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/eventos-productivos")
public class EventoProductivoController {

    private final EventoProductivoService service;

    public EventoProductivoController(EventoProductivoService service) {
        this.service = service;
    }

    @GetMapping
    public Page<EventoProductivo> listar(@PageableDefault(size = 20) Pageable pageable) {
        return service.obtenerEventos(pageable);
    }

    @GetMapping("/animal/{animalId}")
    public Page<EventoProductivo> listarPorAnimal(@PathVariable Long animalId,
                                                  @PageableDefault(size = 20) Pageable pageable) {
        return service.obtenerPorAnimal(animalId, pageable);
    }

    @PostMapping
    public EventoProductivo crear(@RequestBody EventoProductivo evento) {
        return service.guardarEvento(evento);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminarEvento(id);
    }
}

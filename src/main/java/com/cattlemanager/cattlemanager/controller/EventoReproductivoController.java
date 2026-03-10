package com.cattlemanager.cattlemanager.controller;

import com.cattlemanager.cattlemanager.model.EventoReproductivo;
import com.cattlemanager.cattlemanager.service.EventoReproductivoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eventos-reproductivos")
public class EventoReproductivoController {

    private final EventoReproductivoService service;

    public EventoReproductivoController(EventoReproductivoService service) {
        this.service = service;
    }

    @GetMapping
    public List<EventoReproductivo> listar() {
        return service.obtenerEventos();
    }

    @PostMapping
    public EventoReproductivo crear(@RequestBody EventoReproductivo evento) {
        return service.guardarEvento(evento);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminarEvento(id);
    }
}


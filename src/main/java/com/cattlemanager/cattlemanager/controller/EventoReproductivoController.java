package com.cattlemanager.cattlemanager.controller;

import com.cattlemanager.cattlemanager.model.EventoReproductivo;
import com.cattlemanager.cattlemanager.service.EventoReproductivoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/eventos-reproductivos")
public class EventoReproductivoController {

    private final EventoReproductivoService service;

    public EventoReproductivoController(EventoReproductivoService service) {
        this.service = service;
    }

    @GetMapping
    public Page<EventoReproductivo> listar(@PageableDefault(size = 20) Pageable pageable) {
        return service.obtenerEventos(pageable);
    }

    // Historial reproductivo de un animal: base para la UI Android pendiente
    @GetMapping("/animal/{animalId}")
    public Page<EventoReproductivo> listarPorAnimal(@PathVariable Long animalId,
                                                    @PageableDefault(size = 20) Pageable pageable) {
        return service.obtenerPorAnimal(animalId, pageable);
    }

    @PostMapping
    public EventoReproductivo crear(@RequestBody EventoReproductivo evento) {
        return service.guardarEvento(evento);
    }

    @PutMapping("/{id}")
    public EventoReproductivo actualizar(@PathVariable Long id,
                                         @RequestBody EventoReproductivo evento) {
        return service.actualizarEvento(id, evento);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminarEvento(id);
    }
}

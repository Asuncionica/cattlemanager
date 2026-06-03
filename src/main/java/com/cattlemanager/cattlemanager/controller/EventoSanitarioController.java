package com.cattlemanager.cattlemanager.controller;

import com.cattlemanager.cattlemanager.model.EventoSanitario;
import com.cattlemanager.cattlemanager.service.EventoSanitarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eventos-sanitarios")
public class EventoSanitarioController {

    private final EventoSanitarioService service;

    public EventoSanitarioController(EventoSanitarioService service) {
        this.service = service;
    }

    @GetMapping
    public List<EventoSanitario> listar() {
        return service.obtenerEventos();
    }

    @PostMapping
    public EventoSanitario crear(@RequestBody EventoSanitario evento) {
        return service.guardarEvento(evento);
    }

    @PutMapping("/{id}")
    public EventoSanitario actualizar(@PathVariable Long id,
                                      @RequestBody EventoSanitario evento) {
        return service.actualizarEvento(id, evento);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminarEvento(id);
    }
}

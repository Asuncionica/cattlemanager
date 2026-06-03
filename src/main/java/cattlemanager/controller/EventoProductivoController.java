package cattlemanager.controller;

import cattlemanager.model.EventoProductivo;
import cattlemanager.service.EventoProductivoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eventos-productivos")
public class EventoProductivoController {

    private final EventoProductivoService service;

    public EventoProductivoController(EventoProductivoService service) {
        this.service = service;
    }

    @GetMapping
    public List<EventoProductivo> listar() {
        return service.obtenerEventos();
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

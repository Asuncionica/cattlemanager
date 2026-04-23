package cattlemanager.controller;

import cattlemanager.model.EventoReproductivo;
import cattlemanager.service.EventoReproductivoService;
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

    // Historial reproductivo de un animal concreto
    @GetMapping("/animal/{animalId}")
    public List<EventoReproductivo> listarPorAnimal(@PathVariable Long animalId) {
        return service.obtenerPorAnimal(animalId);
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

package cattlemanager.service;

import cattlemanager.model.EventoReproductivo;
import cattlemanager.repository.EventoReproductivoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventoReproductivoService {

    private final EventoReproductivoRepository repository;

    public EventoReproductivoService(EventoReproductivoRepository repository) {
        this.repository = repository;
    }

    public List<EventoReproductivo> obtenerEventos() {
        return repository.findAll();
    }

    public List<EventoReproductivo> obtenerPorAnimal(Long animalId) {
        return repository.findByAnimalId(animalId);
    }

    public EventoReproductivo guardarEvento(EventoReproductivo evento) {
        return repository.save(evento);
    }

    public EventoReproductivo actualizarEvento(Long id, EventoReproductivo actualizado) {
        EventoReproductivo evento = repository.findById(id).orElseThrow();
        evento.setTipo(actualizado.getTipo());
        evento.setDescripcion(actualizado.getDescripcion());
        evento.setFecha(actualizado.getFecha());
        evento.setAnimal(actualizado.getAnimal());
        return repository.save(evento);
    }

    public void eliminarEvento(Long id) {
        repository.deleteById(id);
    }
}

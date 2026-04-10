package com.cattlemanager.cattlemanager.service;

import com.cattlemanager.cattlemanager.model.EventoSanitario;
import com.cattlemanager.cattlemanager.repository.EventoSanitarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EventoSanitarioService {

    private final EventoSanitarioRepository repository;

    public EventoSanitarioService(EventoSanitarioRepository repository) {
        this.repository = repository;
    }

    public Page<EventoSanitario> obtenerEventos(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Page<EventoSanitario> obtenerPorAnimal(Long animalId, Pageable pageable) {
        return repository.findByAnimalId(animalId, pageable);
    }

    public EventoSanitario guardarEvento(EventoSanitario evento) {
        return repository.save(evento);
    }

    public EventoSanitario actualizarEvento(Long id, EventoSanitario eventoActualizado) {
        EventoSanitario evento = repository.findById(id).orElseThrow();
        evento.setTipo(eventoActualizado.getTipo());
        evento.setDescripcion(eventoActualizado.getDescripcion());
        evento.setFecha(eventoActualizado.getFecha());
        evento.setAnimal(eventoActualizado.getAnimal());
        return repository.save(evento);
    }

    public void eliminarEvento(Long id) {
        repository.deleteById(id);
    }
}

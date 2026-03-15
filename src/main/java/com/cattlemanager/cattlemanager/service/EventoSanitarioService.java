package com.cattlemanager.cattlemanager.service;

import com.cattlemanager.cattlemanager.model.EventoSanitario;
import com.cattlemanager.cattlemanager.repository.EventoSanitarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventoSanitarioService {

    private final EventoSanitarioRepository repository;

    public EventoSanitarioService(EventoSanitarioRepository repository) {
        this.repository = repository;
    }

    public List<EventoSanitario> obtenerEventos() {
        return repository.findAll();
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


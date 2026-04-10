package com.cattlemanager.cattlemanager.service;

import com.cattlemanager.cattlemanager.model.EventoReproductivo;
import com.cattlemanager.cattlemanager.repository.EventoReproductivoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EventoReproductivoService {

    private final EventoReproductivoRepository repository;

    public EventoReproductivoService(EventoReproductivoRepository repository) {
        this.repository = repository;
    }

    public Page<EventoReproductivo> obtenerEventos(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Page<EventoReproductivo> obtenerPorAnimal(Long animalId, Pageable pageable) {
        return repository.findByAnimalId(animalId, pageable);
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

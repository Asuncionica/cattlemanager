package com.cattlemanager.cattlemanager.service;

import com.cattlemanager.cattlemanager.model.EventoProductivo;
import com.cattlemanager.cattlemanager.repository.EventoProductivoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EventoProductivoService {

    private final EventoProductivoRepository repository;

    public EventoProductivoService(EventoProductivoRepository repository) {
        this.repository = repository;
    }

    public Page<EventoProductivo> obtenerEventos(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Page<EventoProductivo> obtenerPorAnimal(Long animalId, Pageable pageable) {
        return repository.findByAnimalId(animalId, pageable);
    }

    public EventoProductivo guardarEvento(EventoProductivo evento) {
        return repository.save(evento);
    }

    public void eliminarEvento(Long id) {
        repository.deleteById(id);
    }
}

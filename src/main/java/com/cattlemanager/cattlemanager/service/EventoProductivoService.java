package com.cattlemanager.cattlemanager.service;

import com.cattlemanager.cattlemanager.model.EventoProductivo;
import com.cattlemanager.cattlemanager.repository.EventoProductivoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventoProductivoService {

    private final EventoProductivoRepository repository;

    public EventoProductivoService(EventoProductivoRepository repository) {
        this.repository = repository;
    }

    public List<EventoProductivo> obtenerEventos() {
        return repository.findAll();
    }

    public EventoProductivo guardarEvento(EventoProductivo evento) {
        return repository.save(evento);
    }

    public void eliminarEvento(Long id) {
        repository.deleteById(id);
    }
}


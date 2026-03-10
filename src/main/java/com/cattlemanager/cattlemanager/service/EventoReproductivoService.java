package com.cattlemanager.cattlemanager.service;

import com.cattlemanager.cattlemanager.model.EventoReproductivo;
import com.cattlemanager.cattlemanager.repository.EventoReproductivoRepository;
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

    public EventoReproductivo guardarEvento(EventoReproductivo evento) {
        return repository.save(evento);
    }

    public void eliminarEvento(Long id) {
        repository.deleteById(id);
    }
}


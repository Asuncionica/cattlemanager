package com.cattlemanager.cattlemanager.service;

import com.cattlemanager.cattlemanager.model.Granja;
import com.cattlemanager.cattlemanager.repository.GranjaRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class GranjaService {

    private final GranjaRepository grRepository;

    public GranjaService(GranjaRepository grRepository) {
        this.grRepository = grRepository;
    }

    public List<Granja> obtenerGranjas(){
        return grRepository.findAll();
    }

    public Granja guardar(Granja gr){
        return grRepository.save(gr);
    }

    public void eliminar(Long id){
        grRepository.deleteById(id);
    }

    public Granja actualizar(Long id, Granja gr){
        gr.setId(id);
        return grRepository.save(gr);
    }
}


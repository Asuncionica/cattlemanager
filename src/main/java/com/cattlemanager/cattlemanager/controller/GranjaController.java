package com.cattlemanager.cattlemanager.controller;

import com.cattlemanager.cattlemanager.model.Granja;
import com.cattlemanager.cattlemanager.service.GranjaService;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/granjas")
public class GranjaController {

    private final GranjaService grService;

    public GranjaController(GranjaService grService){
        this.grService = grService;
    }

    @GetMapping
    public List<Granja> listar(){
        return grService.obtenerGranjas();
    }

    @PostMapping
    public Granja crear(@RequestBody Granja gr){
        return grService.guardar(gr);
    }

    @PutMapping("/{id}")
    public Granja actualizar(@PathVariable Long id,
                             @RequestBody Granja gr){
        return grService.actualizar(id, gr);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id){
        grService.eliminar(id);
    }
}

package com.cattlemanager.cattlemanager.controller;

import com.cattlemanager.cattlemanager.model.Granja;
import com.cattlemanager.cattlemanager.service.GranjaService;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/granjas")
public class GranjaController {

    private final GranjaService grService;

    public GranjaController(GranjaService grService){
        this.grService = grService;
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<Granja> listarPorUsuario(@PathVariable Long usuarioId){
        return grService.obtenerGranjasPorUsuario(usuarioId);
    }

    @PostMapping("/usuario/{usuarioId}")
    public Granja crear(@PathVariable Long usuarioId, @RequestBody Granja gr){
        return grService.guardar(usuarioId, gr);
    }

    @PutMapping("/usuario/{usuarioId}/{id}")
    public Granja actualizar(@PathVariable Long usuarioId,
                             @PathVariable Long id,
                             @RequestBody Granja gr){
        return grService.actualizar(usuarioId, id, gr);
    }

    @DeleteMapping("/usuario/{usuarioId}/{id}")
    public void eliminar(@PathVariable Long usuarioId, @PathVariable Long id){
        grService.eliminar(usuarioId, id);
    }
}
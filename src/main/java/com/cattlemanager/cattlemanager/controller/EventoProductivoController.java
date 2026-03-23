package com.cattlemanager.cattlemanager.controller;

import com.cattlemanager.cattlemanager.model.EventoProductivo;
import com.cattlemanager.cattlemanager.service.EventoProductivoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/*Controlador REST encargado de gestionar los endpoints relacionados
 con los eventos productivos del sistema.
 Proporciona operaciones básicas como:
 * - Listar eventos
 * - Crear un nuevo evento
 * - Eliminar un evento existente
 */
@RestController
@RequestMapping("/eventos-productivos")
public class EventoProductivoController {
    //Servicio que contiene la lógica de negocio para los eventos productivos.
    private final EventoProductivoService service;
    /*Constructor con inyección de dependencias.
     @param service Servicio de eventos productivos.*/
    public EventoProductivoController(EventoProductivoService service) {
        this.service = service;
    }
    /*Endpoint GET para obtener la lista de todos los eventos productivos.
      URL: /eventos-productivos
      Método: GET
      @return Lista de eventos productivos.*/
    @GetMapping
    public List<EventoProductivo> listar() {
        return service.obtenerEventos();
    }
     /*Endpoint POST para crear un nuevo evento productivo.
        URL: /eventos-productivos
        Método: POST
        @param evento Objeto EventoProductivo recibido en el cuerpo de la petición
        @return EventoProductivo creado y guardado.*/
    @PostMapping
    public EventoProductivo crear(@RequestBody EventoProductivo evento) {
        return service.guardarEvento(evento);
    }
    /*Endpoint DELETE para eliminar un evento productivo por su ID.
     * URL: /eventos-productivos/{id}
     * Método: DELETE
     * @param id Identificador del evento a eliminar.*/
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminarEvento(id);
    }
}


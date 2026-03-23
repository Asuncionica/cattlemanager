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
/*Este es un CONTROLADOR REST.
  Un controlador es la clase que recibe las peticiones del cliente (por ejemplo,
  desde un frontend o Postman)
  y devuelve respuestas.
  En este caso, gestiona todo lo relacionado con las GRANJAS. */
@RestController// Indica que esta clase es un controlador REST
@RequestMapping("/granjas")// Todas las rutas empezarán por /granjas
public class GranjaController {
    /*Este es el SERVICIO.
      El servicio contiene la lógica del programa (lo importante).
      El controlador solo delega en él.*/
    private final GranjaService grService;
    /* Constructor.
       Spring inyecta automáticamente el servicio aquí.*/
    public GranjaController(GranjaService grService){
        this.grService = grService;
    }
    /* OBTENER TODAS LAS GRANJAS
     * Método: GET
     * URL: /granjas
     * 👉 Devuelve una lista con todas las granjas. */
    @GetMapping
    public List<Granja> listar(){
        return grService.obtenerGranjas();
    }
    /* CREAR UNA NUEVA GRANJA
     * Método: POST
     * URL: /granjas
     * 👉 Recibe una granja en formato JSON en el cuerpo de la petición.
     * 👉 La guarda en la base de datos.
     * @param gr Objeto Granja que viene en el body (JSON)
     * @return La granja guardada.*/
    @PostMapping
    public Granja crear(@RequestBody Granja gr){
        return grService.guardar(gr);
    }
    /* ACTUALIZAR UNA GRANJA EXISTENTE
     * Método: PUT
     * URL: /granjas/{id}
     * 👉 {id} es el identificador de la granja a actualizar.
     * 👉 Los nuevos datos vienen en el body.
     * @param id ID de la granja
     * @param gr Nuevos datos de la granja
     * @return Granja actualizada.*/
    @PutMapping("/{id}")
    public Granja actualizar(@PathVariable Long id,
                             @RequestBody Granja gr){
        return grService.actualizar(id, gr);
    }
    /* ELIMINAR UNA GRANJA
     * Método: DELETE
     * URL: /granjas/{id}
     * 👉 Elimina la granja con el ID indicado.
     * @param id ID de la granja a eliminar.*/
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id){
        grService.eliminar(id);
    }
}

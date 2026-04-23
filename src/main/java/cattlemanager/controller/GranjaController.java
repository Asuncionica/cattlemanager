package cattlemanager.controller;

import cattlemanager.model.Granja;
import cattlemanager.service.GranjaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/granjas")
public class GranjaController {

    private final GranjaService grService;

    public GranjaController(GranjaService grService) {
        this.grService = grService;
    }

    @GetMapping
    public List<Granja> listar() {
        return grService.obtenerGranjas();
    }

    // Endpoint clave para la app Android: carga las granjas del usuario logueado
    // evitando así tener que hardcodear granja_id=1
    @GetMapping("/usuario/{usuarioId}")
    public List<Granja> listarPorUsuario(@PathVariable Long usuarioId) {
        return grService.obtenerPorUsuario(usuarioId);
    }

    @PostMapping
    public Granja crear(@RequestBody Granja gr) {
        return grService.guardar(gr);
    }

    @PutMapping("/{id}")
    public Granja actualizar(@PathVariable Long id, @RequestBody Granja gr) {
        return grService.actualizar(id, gr);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        grService.eliminar(id);
    }
}

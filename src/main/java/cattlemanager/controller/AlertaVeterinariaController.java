package cattlemanager.controller;

import cattlemanager.model.AlertaVeterinaria;
import cattlemanager.service.AlertaVeterinariaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alertas-veterinaria")
public class AlertaVeterinariaController {

    private final AlertaVeterinariaService service;

    public AlertaVeterinariaController(AlertaVeterinariaService service) {
        this.service = service;
    }

    // GET /alertas-veterinaria → todas | ?atendida=false → solo pendientes
    @GetMapping
    public List<AlertaVeterinaria> listar(
            @RequestParam(required = false) Boolean atendida) {

        if (atendida != null) {
            return service.obtenerPorEstado(atendida);
        }
        return service.obtenerTodas();
    }

    @PostMapping
    public AlertaVeterinaria crear(@RequestBody AlertaVeterinaria alerta) {
        return service.guardar(alerta);
    }

    // El veterinario marca la alerta como atendida (no la borra, queda en histórico)
    @PutMapping("/{id}/atender")
    public AlertaVeterinaria atender(@PathVariable Long id) {
        return service.marcarAtendida(id);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}

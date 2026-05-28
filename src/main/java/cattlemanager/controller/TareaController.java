package cattlemanager.controller;

import cattlemanager.model.Tarea;
import cattlemanager.service.TareaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tareas")
public class TareaController {

    private final TareaService tareaService;

    public TareaController(TareaService tareaService) {
        this.tareaService = tareaService;
    }

    @GetMapping
    public Page<Tarea> listar(@PageableDefault(size = 20) Pageable pageable) {
        return tareaService.obtenerTareas(pageable);
    }

    // Tareas de una granja; ?completada=false devuelve solo las pendientes
    @GetMapping("/granja/{granjaId}")
    public Page<Tarea> listarPorGranja(
            @PathVariable Long granjaId,
            @RequestParam(required = false) Boolean completada,
            @PageableDefault(size = 20) Pageable pageable) {

        if (completada != null) {
            return tareaService.obtenerPorGranjaYEstado(granjaId, completada, pageable);
        }
        return tareaService.obtenerPorGranja(granjaId, pageable);
    }

    @PostMapping
    public Tarea crear(@RequestBody Tarea tarea) {
        return tareaService.guardar(tarea);
    }

    @PutMapping("/{id}")
    public Tarea actualizar(@PathVariable Long id, @RequestBody Tarea tarea) {
        return tareaService.actualizar(id, tarea);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        tareaService.eliminar(id);
    }
}

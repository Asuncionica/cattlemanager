package cattlemanager.controller;

import cattlemanager.model.Rol;
import cattlemanager.service.RolService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roles")
public class RolController {

    private final RolService rolService;

    public RolController(RolService rolService) {
        this.rolService = rolService;
    }

    @GetMapping
    public Page<Rol> listar(@PageableDefault(size = 20) Pageable pageable) {
        return rolService.obtenerRoles(pageable);
    }

    @GetMapping("/{id}")
    public Rol obtenerPorId(@PathVariable Long id) {
        return rolService.obtenerPorId(id);
    }

    @PostMapping
    public Rol crear(@RequestBody Rol rol) {
        return rolService.guardar(rol);
    }

    @PutMapping("/{id}")
    public Rol actualizar(@PathVariable Long id, @RequestBody Rol rol) {
        return rolService.actualizar(id, rol);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        rolService.eliminar(id);
    }
}

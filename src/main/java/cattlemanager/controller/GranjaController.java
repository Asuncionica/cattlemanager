package cattlemanager.controller;

import cattlemanager.model.Granja;
import cattlemanager.model.Usuario;
import cattlemanager.service.GranjaService;
import cattlemanager.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/granjas")
public class GranjaController {

    private final GranjaService grService;
    private final UsuarioService usuarioService;

    public GranjaController(GranjaService grService, UsuarioService usuarioService) {
        this.grService = grService;
        this.usuarioService = usuarioService;
    }

    // Solo el ENCARGADO puede ver todas las granjas del sistema
    @GetMapping
    public ResponseEntity<List<Granja>> listar(Authentication auth) {
        boolean isEncargado = auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ENCARGADO"));
        if (!isEncargado) {
            Usuario solicitante = usuarioService.findByEmail(auth.getName());
            if (solicitante == null) return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            return ResponseEntity.ok(grService.obtenerPorUsuario(solicitante.getId()));
        }
        return ResponseEntity.ok(grService.obtenerGranjas());
    }

    // Solo puede consultarse si el token pertenece al mismo usuario o es ENCARGADO
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Granja>> listarPorUsuario(@PathVariable Long usuarioId,
                                                          Authentication auth) {
        boolean isEncargado = auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ENCARGADO"));
        if (!isEncargado) {
            Usuario solicitante = usuarioService.findByEmail(auth.getName());
            if (solicitante == null || !solicitante.getId().equals(usuarioId)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
        }
        return ResponseEntity.ok(grService.obtenerPorUsuario(usuarioId));
    }

    @PostMapping
    public ResponseEntity<Granja> crear(@RequestBody Granja gr) {
        return ResponseEntity.status(HttpStatus.CREATED).body(grService.guardar(gr));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Granja> actualizar(@PathVariable Long id, @RequestBody Granja gr) {
        return ResponseEntity.ok(grService.actualizar(id, gr));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        grService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}

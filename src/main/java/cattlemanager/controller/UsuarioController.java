package cattlemanager.controller;

import cattlemanager.dto.LoginRequestDto;
import cattlemanager.model.Usuario;
import cattlemanager.security.JwtUtil;
import cattlemanager.security.LoginRateLimiter;
import cattlemanager.service.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final JwtUtil jwtUtil;
    private final LoginRateLimiter rateLimiter;

    public UsuarioController(UsuarioService usuarioService, JwtUtil jwtUtil, LoginRateLimiter rateLimiter) {
        this.usuarioService = usuarioService;
        this.jwtUtil = jwtUtil;
        this.rateLimiter = rateLimiter;
    }

    @GetMapping
    public List<Usuario> obtenerUsuarios() {
        return usuarioService.obtenerUsuarios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable Long id) {
        Usuario u = usuarioService.obtenerUsuarioPorId(id);
        return u != null ? ResponseEntity.ok(u) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Usuario> crearUsuario(@Valid @RequestBody Usuario usuario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.guardarUsuario(usuario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        Usuario actualizado = usuarioService.actualizarUsuario(id, usuario);
        return actualizado != null ? ResponseEntity.ok(actualizado) : ResponseEntity.notFound().build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDto request,
                                   HttpServletRequest httpRequest) {
        String clienteIp = obtenerIp(httpRequest);
        if (!rateLimiter.isPermitido(clienteIp)) {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                    .body(Map.of("error", "Demasiados intentos. Espera 1 minuto."));
        }

        Usuario autenticado = usuarioService.login(request.getEmail(), request.getPassword());
        if (autenticado == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Credenciales incorrectas"));
        }

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("token", jwtUtil.generarToken(
                autenticado.getEmail(),
                autenticado.getId(),
                autenticado.getRol() != null ? autenticado.getRol().getNombre() : null
        ));
        respuesta.put("id", autenticado.getId());
        respuesta.put("nombre", autenticado.getNombre());
        respuesta.put("email", autenticado.getEmail());
        respuesta.put("rol", autenticado.getRol());
        return ResponseEntity.ok(respuesta);
    }

    private String obtenerIp(HttpServletRequest request) {
        String forwarded = request.getHeader("X-Forwarded-For");
        if (forwarded != null && !forwarded.isBlank()) {
            return forwarded.split(",")[0].trim();
        }
        return request.getRemoteAddr();
    }
}

package com.cattlemanager.cattlemanager.controller;

import com.cattlemanager.cattlemanager.model.Usuario;
import com.cattlemanager.cattlemanager.security.JwtUtil;
import com.cattlemanager.cattlemanager.service.UsuarioService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final JwtUtil jwtUtil;

    public UsuarioController(UsuarioService usuarioService, JwtUtil jwtUtil) {
        this.usuarioService = usuarioService;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping
    public Page<Usuario> obtenerUsuarios(@PageableDefault(size = 20) Pageable pageable) {
        return usuarioService.obtenerUsuarios(pageable);
    }

    @GetMapping("/{id}")
    public Usuario obtenerUsuarioPorId(@PathVariable Long id) {
        return usuarioService.obtenerUsuarioPorId(id);
    }

    @PostMapping
    public Usuario crearUsuario(@RequestBody Usuario usuario) {
        return usuarioService.guardarUsuario(usuario);
    }

    @DeleteMapping("/{id}")
    public void eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
    }

    @PutMapping("/{id}")
    public Usuario actualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        return usuarioService.actualizarUsuario(id, usuario);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario usuario) {
        Usuario autenticado = usuarioService.login(usuario.getEmail(), usuario.getPassword());
        if (autenticado == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
        }
        // Devuelve el token y datos básicos del usuario (nunca la contraseña)
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("token", jwtUtil.generarToken(autenticado.getEmail(), autenticado.getId()));
        respuesta.put("id", autenticado.getId());
        respuesta.put("nombre", autenticado.getNombre());
        respuesta.put("email", autenticado.getEmail());
        respuesta.put("rol", autenticado.getRol());
        return ResponseEntity.ok(respuesta);
    }
}

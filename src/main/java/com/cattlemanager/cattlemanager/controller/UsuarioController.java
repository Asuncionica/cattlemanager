package com.cattlemanager.cattlemanager.controller;

import com.cattlemanager.cattlemanager.model.Usuario;
import com.cattlemanager.cattlemanager.service.UsuarioService;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<Usuario> obtenerUsuarios(){
        return usuarioService.obtenerUsuarios();
    }
    @GetMapping("/{id}")
    public Usuario obtenerUsuarioPorId(@PathVariable Long id) {
        return usuarioService.obtenerUsuarioPorId(id);
    }

    @PostMapping
    public Usuario crearUsuario(@RequestBody Usuario usuario){
        return usuarioService.guardarUsuario(usuario);
    }

    @DeleteMapping("/{id}")
    public void eliminarUsuario(@PathVariable Long id){
        usuarioService.eliminarUsuario(id);
    }
    @PutMapping("/{id}")
    public Usuario actualizarUsuario(@PathVariable Long id,
                                 @RequestBody Usuario usuario) {
        return usuarioService.actualizarUsuario(id, usuario);
    }
    @PostMapping("/login")
    public Usuario login(@RequestBody Usuario usuario){
        return usuarioService.login(usuario.getEmail(), usuario.getPassword());
    }


}

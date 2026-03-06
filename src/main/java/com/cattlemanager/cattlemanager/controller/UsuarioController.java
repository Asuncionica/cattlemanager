package com.cattlemanager.cattlemanager.controller;

import com.cattlemanager.cattlemanager.model.Usuario;
import com.cattlemanager.cattlemanager.repository.UsuarioRepository;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping
    public List<Usuario> listarUsuarios(){
        return usuarioRepository.findAll();
    }

    @GetMapping("/{id}")
    public Usuario obtenerUsuario(@PathVariable Long id){
        return usuarioRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Usuario crearUsuario(@RequestBody Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    @PutMapping("/{id}")
    public Usuario actualizarUsuario(@PathVariable Long id,
                                      @RequestBody Usuario usuario){

        return usuarioRepository.findById(id).map(u -> {
            u.setNombre(usuario.getNombre());
            u.setEmail(usuario.getEmail());
            u.setPassword(usuario.getPassword());
            u.setRol(usuario.getRol());
            return usuarioRepository.save(u);
        }).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void eliminarUsuario(@PathVariable Long id){
        usuarioRepository.deleteById(id);
    }
    @PostMapping("/login")
    public Usuario login(@RequestBody Usuario usuario){

        return usuarioRepository.findAll().stream()
            .filter(u ->
                    u.getEmail().equals(usuario.getEmail()) &&
                    u.getPassword().equals(usuario.getPassword())
            )
            .findFirst()
            .orElse(null);
    }

}

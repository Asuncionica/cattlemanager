package com.cattlemanager.cattlemanager.service;

import com.cattlemanager.cattlemanager.model.Usuario;
import com.cattlemanager.cattlemanager.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> obtenerUsuarios() {
        return usuarioRepository.findAll();
    }
    
    public Usuario obtenerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }


    public Usuario guardarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
    public Usuario actualizarUsuario(Long id, Usuario usuario) {
    return usuarioRepository.findById(id).map(u -> {
        u.setNombre(usuario.getNombre());
        u.setEmail(usuario.getEmail());
        u.setPassword(usuario.getPassword());
        u.setRol(usuario.getRol());
        return usuarioRepository.save(u);
    }).orElse(null);
    }
    
    public Usuario login(String email, String password) {
        return usuarioRepository.findByEmailAndPassword(email, password);
    }
}


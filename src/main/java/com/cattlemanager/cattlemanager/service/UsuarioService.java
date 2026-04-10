package com.cattlemanager.cattlemanager.service;

import com.cattlemanager.cattlemanager.model.Usuario;
import com.cattlemanager.cattlemanager.repository.UsuarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, BCryptPasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Page<Usuario> obtenerUsuarios(Pageable pageable) {
        return usuarioRepository.findAll(pageable);
    }

    public Usuario obtenerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario guardarUsuario(Usuario usuario) {
        // Siempre hashear la contraseña antes de persistir
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usuarioRepository.save(usuario);
    }

    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    public Usuario actualizarUsuario(Long id, Usuario usuario) {
        return usuarioRepository.findById(id).map(u -> {
            u.setNombre(usuario.getNombre());
            u.setEmail(usuario.getEmail());
            // Solo se rehashea si el cliente envía una contraseña nueva
            if (usuario.getPassword() != null && !usuario.getPassword().isBlank()) {
                u.setPassword(passwordEncoder.encode(usuario.getPassword()));
            }
            u.setRol(usuario.getRol());
            return usuarioRepository.save(u);
        }).orElse(null);
    }

    // Devuelve el usuario si las credenciales son correctas, null si no
    public Usuario login(String email, String password) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        if (usuario == null || !passwordEncoder.matches(password, usuario.getPassword())) {
            return null;
        }
        return usuario;
    }
}

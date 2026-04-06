package com.cattlemanager.cattlemanager.service;

import com.cattlemanager.cattlemanager.model.Granja;
import com.cattlemanager.cattlemanager.model.Usuario;
import com.cattlemanager.cattlemanager.repository.GranjaRepository;
import com.cattlemanager.cattlemanager.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GranjaService {

    private final GranjaRepository grRepository;
    private final UsuarioRepository usuarioRepository;

    public GranjaService(GranjaRepository grRepository, UsuarioRepository usuarioRepository) {
        this.grRepository = grRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<Granja> obtenerGranjasPorUsuario(Long usuarioId) {
        return grRepository.findByUsuarioId(usuarioId);
    }

    public Granja guardar(Long usuarioId, Granja gr) {
        Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);

        if (usuario == null) {
            return null;
        }

        if (usuario.getRol() == null || !usuario.getRol().getId().equals(2L)) {
            return null;
        }

        gr.setId(null);
        gr.setUsuario(usuario);

        return grRepository.save(gr);
    }

    public void eliminar(Long usuarioId, Long id) {
        Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);

        if (usuario == null) {
            return;
        }

        if (usuario.getRol() == null || !usuario.getRol().getId().equals(2L)) {
            return;
        }

        Granja granja = grRepository.findByIdAndUsuarioId(id, usuarioId).orElse(null);

        if (granja != null) {
            grRepository.delete(granja);
        }
    }

    public Granja actualizar(Long usuarioId, Long id, Granja gr) {
        Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);

        if (usuario == null) {
            return null;
        }

        if (usuario.getRol() == null || !usuario.getRol().getId().equals(2L)) {
            return null;
        }

        Granja existente = grRepository.findByIdAndUsuarioId(id, usuarioId).orElse(null);

        if (existente == null) {
            return null;
        }

        existente.setNombre(gr.getNombre());
        existente.setUbicacion(gr.getUbicacion());
        existente.setTelefono(gr.getTelefono());

        return grRepository.save(existente);
    }
}
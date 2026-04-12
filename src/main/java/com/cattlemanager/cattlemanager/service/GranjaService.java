package com.cattlemanager.cattlemanager.service;

// Importamos las clases del modelo que vamos a usar
import com.cattlemanager.cattlemanager.model.Granja;
import com.cattlemanager.cattlemanager.model.Usuario;

// Importamos los repositorios para acceder a la base de datos
import com.cattlemanager.cattlemanager.repository.GranjaRepository;
import com.cattlemanager.cattlemanager.repository.UsuarioRepository;

// Esta anotación le dice a Spring que esta clase es un servicio
// Un servicio suele contener la lógica de negocio de la aplicación
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GranjaService {

    // Repositorio para trabajar con las granjas en la base de datos
    private final GranjaRepository grRepository;

    // Repositorio para trabajar con los usuarios en la base de datos
    private final UsuarioRepository usuarioRepository;

    /**
     * Constructor de la clase.
     * Spring usa este constructor para inyectar automáticamente
     * los repositorios que necesita este servicio.
     *
     * @param grRepository repositorio de granjas
     * @param usuarioRepository repositorio de usuarios
     */
    public GranjaService(GranjaRepository grRepository, UsuarioRepository usuarioRepository) {
        this.grRepository = grRepository;
        this.usuarioRepository = usuarioRepository;
    }

    /**
     * Obtiene todas las granjas que pertenecen a un usuario concreto.
     *
     * @param usuarioId ID del usuario
     * @return lista de granjas asociadas a ese usuario
     */
    public List<Granja> obtenerGranjasPorUsuario(Long usuarioId) {
        return grRepository.findByUsuarioId(usuarioId);
    }

    /**
     * Guarda una nueva granja para un usuario.
     *
     * Pasos que sigue:
     * 1. Busca al usuario por su ID.
     * 2. Si el usuario no existe, devuelve null.
     * 3. Comprueba que el usuario tenga el rol permitido (rol con ID 2).
     * 4. Si no tiene ese rol, devuelve null.
     * 5. Fuerza que la granja sea nueva poniendo su ID a null.
     * 6. Asocia la granja al usuario.
     * 7. Guarda la granja en la base de datos.
     *
     * @param usuarioId ID del usuario que quiere crear la granja
     * @param gr objeto Granja con los datos a guardar
     * @return la granja guardada, o null si no se pudo guardar
     */
    public Granja guardar(Long usuarioId, Granja gr) {
        // Buscar usuario en la base de datos
        Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);

        // Si no existe el usuario, no se puede guardar la granja
        if (usuario == null) {
            return null;
        }

        // Validamos que el usuario tenga rol con ID 2
        // Si no tiene rol o no es el rol correcto, no permitimos guardar
        if (usuario.getRol() == null || !usuario.getRol().getId().equals(2L)) {
            return null;
        }

        // Ponemos el ID a null para asegurarnos de que sea una inserción nueva
        gr.setId(null);

        // Asociamos la granja al usuario
        gr.setUsuario(usuario);

        // Guardamos en la base de datos y devolvemos el objeto guardado
        return grRepository.save(gr);
    }

    /**
     * Elimina una granja, pero solo si:
     * - el usuario existe,
     * - tiene el rol permitido,
     * - y la granja pertenece a ese usuario.
     *
     * @param usuarioId ID del usuario que quiere eliminar la granja
     * @param id ID de la granja a eliminar
     */
    public void eliminar(Long usuarioId, Long id) {
        // Buscar usuario
        Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);

        // Si no existe, terminamos el método
        if (usuario == null) {
            return;
        }

        // Si no tiene el rol correcto, no permitimos eliminar
        if (usuario.getRol() == null || !usuario.getRol().getId().equals(2L)) {
            return;
        }

        // Buscar la granja por su ID y comprobar que pertenece a ese usuario
        Granja granja = grRepository.findByIdAndUsuarioId(id, usuarioId).orElse(null);

        // Si la granja existe, la eliminamos
        if (granja != null) {
            grRepository.delete(granja);
        }
    }

    /**
     * Actualiza los datos de una granja existente.
     *
     * Solo se permite actualizar si:
     * - el usuario existe,
     * - tiene el rol permitido,
     * - y la granja pertenece a ese usuario.
     *
     * Campos que actualiza:
     * - nombre
     * - ubicación
     * - teléfono
     *
     * @param usuarioId ID del usuario que quiere actualizar la granja
     * @param id ID de la granja a actualizar
     * @param gr objeto con los nuevos datos
     * @return la granja actualizada, o null si no se pudo actualizar
     */
    public Granja actualizar(Long usuarioId, Long id, Granja gr) {
        // Buscar usuario
        Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);

        // Si no existe, no se puede actualizar
        if (usuario == null) {
            return null;
        }

        // Validar rol
        if (usuario.getRol() == null || !usuario.getRol().getId().equals(2L)) {
            return null;
        }

        // Buscar la granja que queremos actualizar y comprobar que sea del usuario
        Granja existente = grRepository.findByIdAndUsuarioId(id, usuarioId).orElse(null);

        // Si no existe esa granja, devolvemos null
        if (existente == null) {
            return null;
        }

        // Actualizamos solo algunos campos
        existente.setNombre(gr.getNombre());
        existente.setUbicacion(gr.getUbicacion());
        existente.setTelefono(gr.getTelefono());

        // Guardamos los cambios en la base de datos
        return grRepository.save(existente);
    }
}
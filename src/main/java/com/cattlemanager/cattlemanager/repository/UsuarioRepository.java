package com.cattlemanager.cattlemanager.repository;

import com.cattlemanager.cattlemanager.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    /* Método personalizado para buscar un usuario por email y contraseña.
     * @param email La dirección de correo electrónico del usuario.
     * @param password La contraseña del usuario.
     * @return El usuario que coincide con el email y la contraseña, o null si no existe.
     * Nota: Para producción, es recomendable **no almacenar contraseñas en texto plano**.
     * Se debería usar hashing (BCrypt, por ejemplo) para seguridad.*/
    Usuario findByEmailAndPassword(String email, String password);
}


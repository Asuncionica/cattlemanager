package cattlemanager.repository;

import cattlemanager.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // BCrypt no permite buscar por password en BD; se busca por email y se compara el hash en el servicio
    Usuario findByEmail(String email);
}


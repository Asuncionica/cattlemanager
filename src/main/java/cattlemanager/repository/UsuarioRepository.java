package cattlemanager.repository;
// Importa la entidad Usuario que será gestionada por este repositorio
import cattlemanager.model.Usuario;
// JpaRepository proporciona automáticamente operaciones CRUD
// (Crear, Leer, Actualizar y Eliminar) sobre la base de datos.
import org.springframework.data.jpa.repository.JpaRepository;
// Indica que esta interfaz es un componente de acceso a datos
// y será gestionada por Spring.
import org.springframework.stereotype.Repository;
/*Repositorio encargado de acceder a la tabla Usuario.
   Al extender JpaRepository<Usuario, Long>, Spring genera
   automáticamente numerosos métodos sin necesidad de programarlos:
   - save()
   - findAll()
   - findById()
   - deleteById()
   - existsById()
   - count()
   El primer parámetro (Usuario) indica la entidad que gestiona.
   El segundo parámetro (Long) indica el tipo de la clave primaria.*/
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    /* Busca un usuario utilizando su email.
       Spring Data JPA genera automáticamente la consulta SQL
       a partir del nombre del método:
       SELECT * FROM usuario
       WHERE email = ?;
       Este método se utiliza durante el proceso de autenticación.
       No se busca por contraseña porque BCrypt almacena un hash
       diferente incluso para la misma contraseña. Por ello:
       1. Se busca el usuario por email.
       2. Se obtiene la contraseña cifrada almacenada.
       3. En el servicio se compara la contraseña introducida
          con el hash mediante BCrypt.*/
     Usuario findByEmail(String email);
}


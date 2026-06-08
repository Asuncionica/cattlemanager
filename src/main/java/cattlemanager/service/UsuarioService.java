package cattlemanager.service;
// Importa la entidad Usuario
import cattlemanager.model.Usuario;
// Importa el repositorio encargado del acceso a la base de datos
import cattlemanager.repository.UsuarioRepository;
// Clase utilizada para cifrar y verificar contraseñas mediante BCrypt
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// Indica que esta clase pertenece a la capa de servicios
// y contiene la lógica de negocio de la aplicación.
import org.springframework.stereotype.Service;

import java.util.List;
/*
 * Servicio encargado de gestionar todas las operaciones
 * relacionadas con los usuarios.
 *
 * Esta capa actúa como intermediaria entre el controlador
 * (Controller) y el repositorio (Repository), aplicando
 * la lógica de negocio antes de acceder a la base de datos.
 */
@Service
public class UsuarioService {
    //Repositorio que permite acceder a la tabla Usuario.
    private final UsuarioRepository usuarioRepository;
    //Objeto encargado de cifrar y comprobar contraseñas utilizando el algoritmo BCrypt.
    private final BCryptPasswordEncoder passwordEncoder;
    //Constructor del servicio. Spring inyecta automáticamente las dependencias.
    public UsuarioService(UsuarioRepository usuarioRepository, BCryptPasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }
    /*
     * Obtiene la lista completa de usuarios.
     *
     * Internamente utiliza el método findAll() proporcionado
     * por JpaRepository.
     */
    public List<Usuario> obtenerUsuarios() {
        return usuarioRepository.findAll();
    }
    /*
     * Busca un usuario mediante su identificador.
     *
     * Si existe lo devuelve.
     * Si no existe devuelve null.
     */
    public Usuario obtenerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }
    /*
     * Guarda un nuevo usuario en la base de datos.
     *
     * Antes de almacenarlo, la contraseña se cifra mediante
     * BCrypt para evitar guardar contraseñas en texto plano.
     */
    public Usuario guardarUsuario(Usuario usuario) {
        // Cifra la contraseña antes de guardarla
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        // Guarda el usuario en la base de datos
        return usuarioRepository.save(usuario);
    }
    /*
     * Elimina un usuario utilizando su identificador.
     */
    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
    /*
     * Actualiza los datos de un usuario existente.
     *
     * Si el usuario no existe devuelve null.
     *
     * La contraseña solamente se vuelve a cifrar cuando
     * el cliente envía una contraseña nueva.
     */
    public Usuario actualizarUsuario(Long id, Usuario usuario) {
        return usuarioRepository.findById(id).map(u -> {
            // Actualiza el nombre
            u.setNombre(usuario.getNombre());
            // Actualiza el email
            u.setEmail(usuario.getEmail());
            /*
             * Si el cliente envía una nueva contraseña,
             * ésta se cifra nuevamente con BCrypt.
             *
             * Si no envía contraseña, se mantiene la existente.
             */
            if (usuario.getPassword() != null && !usuario.getPassword().isBlank()) {
                u.setPassword(passwordEncoder.encode(usuario.getPassword()));
            }
            // Actualiza el rol del usuario
            u.setRol(usuario.getRol());
            // Guarda los cambios en la base de datos
            return usuarioRepository.save(u);
        }).orElse(null);
    }
    /*
     * Busca un usuario mediante su email.
     *
     * Este método se utiliza principalmente durante
     * el proceso de autenticación.
     */
    public Usuario findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
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

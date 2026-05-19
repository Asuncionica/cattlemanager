package cattlemanager;

import cattlemanager.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

// Al arrancar el backend, convierte a BCrypt cualquier contraseña en texto plano
// (detecta que no empieza por $2a$, que es el prefijo de BCrypt)
@Component
public class DataInitializer implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public DataInitializer(UsuarioRepository usuarioRepository,
                           BCryptPasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        usuarioRepository.findAll().forEach(u -> {
            if (u.getPassword() == null || !u.getPassword().startsWith("$2a$")) {
                u.setPassword(passwordEncoder.encode(u.getPassword()));
                usuarioRepository.save(u);
            }
        });
    }
}

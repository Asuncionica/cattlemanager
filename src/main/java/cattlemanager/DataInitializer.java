package cattlemanager;

import cattlemanager.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

// Al arrancar el backend, convierte a BCrypt cualquier contraseña en texto plano.
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
            if (u.getPassword() != null && !esHashBCrypt(u.getPassword())) {
                u.setPassword(passwordEncoder.encode(u.getPassword()));
                usuarioRepository.save(u);
            }
        });
    }

    private boolean esHashBCrypt(String password) {
        return password.matches("^\\$2[aby]\\$\\d{2}\\$.{53}$");
    }
}

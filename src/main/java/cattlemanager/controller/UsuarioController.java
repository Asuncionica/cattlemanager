package cattlemanager.controller;
// Importa el DTO que recibe el email y la contraseña para el login
import cattlemanager.dto.LoginRequestDto;
// Importa la entidad Usuario
import cattlemanager.model.Usuario;
// Clase encargada de generar y validar los tokens JWT
import cattlemanager.security.JwtUtil;
// Clase que limita el número de intentos de login para evitar ataques de fuerza bruta
import cattlemanager.security.LoginRateLimiter;
// Servicio que contiene la lógica de negocio de los usuarios
import cattlemanager.service.UsuarioService;
// Permite obtener información de la petición HTTP (por ejemplo la IP)
import jakarta.servlet.http.HttpServletRequest;
// Activa las validaciones de los objetos recibidos
import jakarta.validation.Valid;
// Clases para construir respuestas HTTP
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
// Anotaciones de Spring para crear una API REST
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//Indica que esta clase es un controlador REST. Todos los métodos devolverán 
//respuestas en formato JSON.
@RestController
//Define la ruta base de este controlador. Todas las peticiones comenzarán por /usuarios
@RequestMapping("/usuarios")
public class UsuarioController {
    //Servicio encargado de gestionar la lógica de negocio relacionada con los usuarios.
    private final UsuarioService usuarioService;
    //Utilidad para generar los tokens JWT que utilizará el usuario una vez autenticado.
    private final JwtUtil jwtUtil;
    //Controla el número de intentos de login permitidos desde una misma dirección IP.
    private final LoginRateLimiter rateLimiter;
    //Constructor del controlador. Spring inyecta automáticamente las dependencias.
    public UsuarioController(UsuarioService usuarioService, JwtUtil jwtUtil, LoginRateLimiter rateLimiter) {
        this.usuarioService = usuarioService;
        this.jwtUtil = jwtUtil;
        this.rateLimiter = rateLimiter;
    }
    //GET /usuarios. Devuelve la lista completa de usuarios.
    @GetMapping
    public List<Usuario> obtenerUsuarios() {
        return usuarioService.obtenerUsuarios();
    }
    //GET /usuarios/{id}. Busca un usuario por su identificador.
    // Si existe devuelve HTTP 200. Si no existe devuelve HTTP 404.
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable Long id) {
        Usuario u = usuarioService.obtenerUsuarioPorId(id);
        return u != null ? ResponseEntity.ok(u) : ResponseEntity.notFound().build();
    }
    //POST /usuarios. Crea un nuevo usuario. @Valid ejecuta automáticamente las
    //validaciones definidas en la entidad o DTO. Devuelve HTTP 201 (Created).
    @PostMapping
    public ResponseEntity<Usuario> crearUsuario(@Valid @RequestBody Usuario usuario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.guardarUsuario(usuario));
    }
    //DELETE /usuarios/{id}. Elimina un usuario mediante su id. Devuelve HTTP 204 (No Content).
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }
    //PUT /usuarios/{id}. Actualiza los datos de un usuario. Si existe devuelve HTTP 200.
    //Si no existe devuelve HTTP 404.
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        Usuario actualizado = usuarioService.actualizarUsuario(id, usuario);
        return actualizado != null ? ResponseEntity.ok(actualizado) : ResponseEntity.notFound().build();
    }
    //POST /usuarios/login. Realiza la autenticación del usuario. Si las credenciales son correctas:
    // - Genera un token JWT. - Devuelve la información del usuario.
    //Si son incorrectas: - Devuelve HTTP 401. Además limita el número de intentos desde una misma IP
    //para evitar ataques de fuerza bruta.
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDto request,
                                   HttpServletRequest httpRequest) {
        // Obtiene la dirección IP del cliente
        String clienteIp = obtenerIp(httpRequest);
        // Comprueba si ha superado el límite de intentos
        if (!rateLimiter.isPermitido(clienteIp)) {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                    .body(Map.of("error", "Demasiados intentos. Espera 1 minuto."));
        }
        // Intenta autenticar al usuario
        Usuario autenticado = usuarioService.login(request.getEmail(), request.getPassword());
        // Si las credenciales son incorrectas
        if (autenticado == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Credenciales incorrectas"));
        }
        //Si el login es correcto, se prepara la respuesta que se enviará al cliente.
        Map<String, Object> respuesta = new HashMap<>();
        // Genera el token JWT
        respuesta.put("token", jwtUtil.generarToken(
                autenticado.getEmail(),
                autenticado.getId(),
                autenticado.getRol() != null ? autenticado.getRol().getNombre() : null
        ));
        // Añade información del usuario
        respuesta.put("id", autenticado.getId());
        respuesta.put("nombre", autenticado.getNombre());
        respuesta.put("email", autenticado.getEmail());
        respuesta.put("rol", autenticado.getRol());
        // Devuelve HTTP 200 con los datos del usuario y el token
        return ResponseEntity.ok(respuesta);
    }
    //Obtiene la dirección IP real del cliente.Si la aplicación está detrás de un proxy,
    //utiliza la cabecera X-Forwarded-For. En caso contrario utiliza la IP de la conexión.
    private String obtenerIp(HttpServletRequest request) {
        String forwarded = request.getHeader("X-Forwarded-For");
        if (forwarded != null && !forwarded.isBlank()) {
            return forwarded.split(",")[0].trim();
        }
        return request.getRemoteAddr();
    }
}

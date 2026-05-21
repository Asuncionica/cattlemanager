package cattlemanager.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.text.Normalizer;
import java.util.Locale;

@Component
public class JwtUtil {

    @Value("${app.jwt.secret}")
    private String secret;

    @Value("${app.jwt.expiration}")
    private long expiration;

    // Construye la clave HMAC a partir del secreto en Base64
    private SecretKey getKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }

    // Genera un token con el email como subject, el id de usuario y el rol como claims
    public String generarToken(String email, Long userId, String role) {
        return Jwts.builder()
                .subject(email)
                .claim("userId", userId)
                .claim("role", normalizarRol(role))
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getKey(), Jwts.SIG.HS256)
                .compact();
    }

    public String extraerEmail(String token) {
        return parsear(token).getSubject();
    }

    public Long extraerUserId(String token) {
        return parsear(token).get("userId", Long.class);
    }

    public String extraerRol(String token) {
        return parsear(token).get("role", String.class);
    }

    // Devuelve true si la firma es válida y el token no ha expirado
    public boolean esValido(String token) {
        try {
            parsear(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    private Claims parsear(String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private String normalizarRol(String role) {
        if (role == null || role.isBlank()) {
            return null;
        }
        String sinAcentos = Normalizer.normalize(role, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "");
        return sinAcentos.toUpperCase(Locale.ROOT).replaceAll("[^A-Z0-9]+", "_");
    }
}

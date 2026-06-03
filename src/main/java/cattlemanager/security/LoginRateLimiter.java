package cattlemanager.security;

import org.springframework.stereotype.Component;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class LoginRateLimiter {

    private static final int MAX_INTENTOS = 5;
    private static final long VENTANA_MS = 60_000;

    private final ConcurrentHashMap<String, Deque<Long>> intentos = new ConcurrentHashMap<>();

    public boolean isPermitido(String clave) {
        long ahora = System.currentTimeMillis();
        intentos.compute(clave, (k, deque) -> {
            if (deque == null) deque = new ArrayDeque<>();
            while (!deque.isEmpty() && ahora - deque.peekFirst() > VENTANA_MS) {
                deque.pollFirst();
            }
            deque.addLast(ahora);
            return deque;
        });
        return intentos.get(clave).size() <= MAX_INTENTOS;
    }
}

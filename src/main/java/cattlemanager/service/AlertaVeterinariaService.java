package cattlemanager.service;

import cattlemanager.model.AlertaVeterinaria;
import cattlemanager.repository.AlertaVeterinariaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AlertaVeterinariaService {

    private final AlertaVeterinariaRepository repo;

    public AlertaVeterinariaService(AlertaVeterinariaRepository repo) {
        this.repo = repo;
    }

    public List<AlertaVeterinaria> obtenerTodas() {
        return repo.findAll();
    }

    // Devuelve solo las pendientes o solo las atendidas según el filtro
    public List<AlertaVeterinaria> obtenerPorEstado(boolean atendida) {
        return repo.findByAtendida(atendida);
    }

    public AlertaVeterinaria guardar(AlertaVeterinaria alerta) {
        alerta.setFecha(LocalDate.now());
        alerta.setAtendida(false);
        return repo.save(alerta);
    }

    // El veterinario marca la alerta como atendida sin borrarla (histórico)
    public AlertaVeterinaria marcarAtendida(Long id) {
        AlertaVeterinaria alerta = repo.findById(id).orElseThrow();
        alerta.setAtendida(true);
        return repo.save(alerta);
    }

    public void eliminar(Long id) {
        repo.deleteById(id);
    }
}

package cattlemanager.repository;

import cattlemanager.model.EventoReproductivo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventoReproductivoRepository extends JpaRepository<EventoReproductivo, Long> {
    // Recupera el historial reproductivo de un animal concreto
    List<EventoReproductivo> findByAnimalId(Long animalId);
}

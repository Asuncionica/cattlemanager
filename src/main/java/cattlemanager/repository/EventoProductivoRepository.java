package cattlemanager.repository;

import cattlemanager.model.EventoProductivo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoProductivoRepository extends JpaRepository<EventoProductivo, Long> {
    Page<EventoProductivo> findByAnimalId(Long animalId, Pageable pageable);
}

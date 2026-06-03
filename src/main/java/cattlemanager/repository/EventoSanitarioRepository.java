package cattlemanager.repository;

import cattlemanager.model.EventoSanitario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoSanitarioRepository extends JpaRepository<EventoSanitario, Long> {
    // Recupera el historial sanitario (vacunas, desparasitaciones…) de un animal
    Page<EventoSanitario> findByAnimalId(Long animalId, Pageable pageable);
}

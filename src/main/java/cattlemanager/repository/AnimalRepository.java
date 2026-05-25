package cattlemanager.repository;

import cattlemanager.model.Animal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
    // Permite paginar los animales de una granja específica
    Page<Animal> findByGranjaId(Long granjaId, Pageable pageable);

    boolean existsByLoteGeneticoId(Long loteGeneticoId);
}

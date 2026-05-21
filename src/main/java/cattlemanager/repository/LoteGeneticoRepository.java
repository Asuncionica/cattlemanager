package cattlemanager.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cattlemanager.model.LoteGenetico;

@Repository
public interface LoteGeneticoRepository extends JpaRepository<LoteGenetico, Long> {
}
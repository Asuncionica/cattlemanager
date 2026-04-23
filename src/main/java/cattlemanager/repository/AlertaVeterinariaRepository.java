package cattlemanager.repository;

import cattlemanager.model.AlertaVeterinaria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlertaVeterinariaRepository extends JpaRepository<AlertaVeterinaria, Long> {

    // Filtra por estado: false = pendientes, true = atendidas
    List<AlertaVeterinaria> findByAtendida(boolean atendida);
}

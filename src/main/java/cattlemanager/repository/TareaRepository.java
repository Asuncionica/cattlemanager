package cattlemanager.repository;

import cattlemanager.model.Tarea;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TareaRepository extends JpaRepository<Tarea, Long> {

    // Tareas de una granja
    Page<Tarea> findByGranjaId(Long granjaId, Pageable pageable);

    // Tareas de una granja filtradas por estado
    Page<Tarea> findByGranjaIdAndCompletada(Long granjaId, boolean completada, Pageable pageable);

    // NUEVO: tareas de un peón
    Page<Tarea> findByPeonId(Long peonId, Pageable pageable);

    // NUEVO: tareas de un peón filtradas por estado
    Page<Tarea> findByPeonIdAndCompletada(Long peonId, boolean completada, Pageable pageable);
}

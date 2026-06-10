package cattlemanager.service;

import cattlemanager.model.Tarea;
import cattlemanager.repository.TareaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TareaService {

    private final TareaRepository tareaRepository;

    public TareaService(TareaRepository tareaRepository) {
        this.tareaRepository = tareaRepository;
    }

    public Page<Tarea> obtenerTareas(Pageable pageable) {
        return tareaRepository.findAll(pageable);
    }

    public Page<Tarea> obtenerPorGranja(Long granjaId, Pageable pageable) {
        return tareaRepository.findByGranjaId(granjaId, pageable);
    }

    public Page<Tarea> obtenerPorGranjaYEstado(Long granjaId, boolean completada, Pageable pageable) {
        return tareaRepository.findByGranjaIdAndCompletada(granjaId, completada, pageable);
    }

    // NUEVO: obtener tareas asignadas a un peón
    public Page<Tarea> obtenerPorPeon(Long peonId, Pageable pageable) {
        return tareaRepository.findByPeonId(peonId, pageable);
    }

    // NUEVO: obtener tareas de un peón filtradas por estado
    public Page<Tarea> obtenerPorPeonYEstado(Long peonId, boolean completada, Pageable pageable) {
        return tareaRepository.findByPeonIdAndCompletada(peonId, completada, pageable);
    }

    public Tarea guardar(Tarea tarea) {
        return tareaRepository.save(tarea);
    }

    public Tarea actualizar(Long id, Tarea tareaActualizada) {
        Tarea tarea = tareaRepository.findById(id).orElseThrow();

        tarea.setTitulo(tareaActualizada.getTitulo());
        tarea.setDescripcion(tareaActualizada.getDescripcion());
        tarea.setFechaVencimiento(tareaActualizada.getFechaVencimiento());
        tarea.setCompletada(tareaActualizada.isCompletada());
        tarea.setGranja(tareaActualizada.getGranja());
        tarea.setPeon(tareaActualizada.getPeon());

        return tareaRepository.save(tarea);
    }

    public void eliminar(Long id) {
        tareaRepository.deleteById(id);
    }
}
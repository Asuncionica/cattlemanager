package cattlemanager.service;

import cattlemanager.model.Granja;
import cattlemanager.repository.GranjaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GranjaService {

    private final GranjaRepository grRepository;

    public GranjaService(GranjaRepository grRepository) {
        this.grRepository = grRepository;
    }

    public List<Granja> obtenerGranjas() {
        return grRepository.findAll();
    }

    // Devuelve solo las granjas que pertenecen al usuario indicado
    public List<Granja> obtenerPorUsuario(Long usuarioId) {
        return grRepository.findByUsuarioId(usuarioId);
    }

    public Granja guardar(Granja gr) {
        return grRepository.save(gr);
    }

    public void eliminar(Long id) {
        grRepository.deleteById(id);
    }

    public Granja actualizar(Long id, Granja gr) {
        gr.setId(id);
        return grRepository.save(gr);
    }
}

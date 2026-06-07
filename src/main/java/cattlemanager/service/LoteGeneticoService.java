package cattlemanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import cattlemanager.model.LoteGenetico;
import cattlemanager.repository.AnimalRepository;
import cattlemanager.repository.LoteGeneticoRepository;

@Service
public class LoteGeneticoService {

    @Autowired
    private LoteGeneticoRepository loteGeneticoRepository;

    @Autowired
    private AnimalRepository animalRepository;

    public List<LoteGenetico> getAllLotes() {
        return loteGeneticoRepository.findAll();
    }

    public ResponseEntity<LoteGenetico> createLote(LoteGenetico lote) {

        if (lote.getNombre() == null || lote.getNombre().isBlank()) {
            return ResponseEntity.badRequest().build();
        }

        String nombreLimpio = lote.getNombre().trim();

        if (loteGeneticoRepository.existsByNombreIgnoreCase(nombreLimpio)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        lote.setNombre(nombreLimpio);

        LoteGenetico nuevoLote = loteGeneticoRepository.save(lote);

        return ResponseEntity.ok(nuevoLote);
    }

    public ResponseEntity<Void> deleteLote(Long id) {

        if (!loteGeneticoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        if (animalRepository.existsByLoteGeneticoId(id)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        loteGeneticoRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}

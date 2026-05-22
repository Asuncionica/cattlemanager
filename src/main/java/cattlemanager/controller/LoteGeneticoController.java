package cattlemanager.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cattlemanager.model.LoteGenetico;
import cattlemanager.repository.LoteGeneticoRepository;

@RestController
@RequestMapping("/api/lotes-geneticos")
public class LoteGeneticoController {

    @Autowired
    private LoteGeneticoRepository loteGeneticoRepository;

    @GetMapping
    public List<LoteGenetico> getAllLotes() {
        return loteGeneticoRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<LoteGenetico> createLote(@RequestBody LoteGenetico lote) {
        if (lote.getNombre() == null || lote.getNombre().isBlank()) {
            return ResponseEntity.badRequest().build();
        }
        if (loteGeneticoRepository.existsByNombreIgnoreCase(lote.getNombre().trim())) {
            return ResponseEntity.status(409).build();
        }
        lote.setNombre(lote.getNombre().trim());
        LoteGenetico nuevoLote = loteGeneticoRepository.save(lote);
        return ResponseEntity.ok(nuevoLote);
    }
}

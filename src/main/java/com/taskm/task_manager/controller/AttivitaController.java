package com.taskm.task_manager.controller;

import com.taskm.task_manager.model.Attivita;
import com.taskm.task_manager.service.AttivitaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attivita")
@CrossOrigin(origins = "*")
public class AttivitaController {

    private final AttivitaService service;

    public AttivitaController(AttivitaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Attivita>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/progetto/{idProgetto}")
    public ResponseEntity<List<Attivita>> getByProgetto(@PathVariable Long idProgetto) {
        return ResponseEntity.ok(service.findByProgetto(idProgetto));
    }

    @PostMapping
    public ResponseEntity<Attivita> crea(@RequestBody Attivita attivita) {
        return ResponseEntity.ok(service.salva(attivita));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Attivita> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> elimina(@PathVariable Long id) {
        service.elimina(id);
        return ResponseEntity.noContent().build();
    }
}
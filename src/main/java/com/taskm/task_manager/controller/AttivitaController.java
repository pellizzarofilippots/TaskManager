package com.taskm.task_manager.controller;

import com.taskm.task_manager.model.Attivita;
import com.taskm.task_manager.service.AttivitaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @GetMapping("/{id}")
    public ResponseEntity<Attivita> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/progetto/{idProgetto}")
    public ResponseEntity<List<Attivita>> getByProgetto(@PathVariable Long idProgetto) {
        return ResponseEntity.ok(service.findByProgetto(idProgetto));
    }

    @GetMapping("/persona/{personaId}")
    public ResponseEntity<List<Attivita>> getByPersona(@PathVariable Long personaId) {
        return ResponseEntity.ok(service.findByPersona(personaId));
    }

    @GetMapping("/operatore/{operatoreId}")
    public ResponseEntity<List<Attivita>> getByOperatore(@PathVariable Long operatoreId) {
        return ResponseEntity.ok(service.findByOperatore(operatoreId));
    }

    @GetMapping("/stato/{statoId}")
    public ResponseEntity<List<Attivita>> getByStato(@PathVariable Long statoId) {
        return ResponseEntity.ok(service.findByStato(statoId));
    }

    @GetMapping("/priorita/{prioritaId}")
    public ResponseEntity<List<Attivita>> getByPriorita(@PathVariable Long prioritaId) {
        return ResponseEntity.ok(service.findByPriorita(prioritaId));
    }

    @PostMapping
    public ResponseEntity<Attivita> crea(@RequestBody Attivita attivita) {
        return ResponseEntity.ok(service.salva(attivita));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Attivita> aggiorna(@PathVariable Long id, @RequestBody Attivita attivita) {
        return service.findById(id)
                .map(existing -> {
                    attivita.setId(id);
                    return ResponseEntity.ok(service.salva(attivita));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/stato")
    public ResponseEntity<Attivita> aggiornaStato(@PathVariable Long id, @RequestBody Map<String, Long> payload) {
        Long statoId = payload.get("statoId");
        return service.aggiornaStato(id, statoId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/priorita")
    public ResponseEntity<Attivita> aggiornaPriorita(@PathVariable Long id, @RequestBody Map<String, Long> payload) {
        Long prioritaId = payload.get("prioritaId");
        return service.aggiornaPriorita(id, prioritaId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/assegna")
    public ResponseEntity<Attivita> assegnaPersona(@PathVariable Long id, @RequestBody Map<String, Long> payload) {
        Long personaId = payload.get("personaId");
        return service.assegnaPersona(id, personaId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> elimina(@PathVariable Long id) {
        service.elimina(id);
        return ResponseEntity.noContent().build();
    }
}
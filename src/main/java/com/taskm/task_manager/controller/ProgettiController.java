package com.taskm.task_manager.controller;

import com.taskm.task_manager.dto.ProgettiDto;
import com.taskm.task_manager.model.Progetti;
import com.taskm.task_manager.service.ProgettiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/progetti")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class ProgettiController {

    private final ProgettiService service;

    public ProgettiController(ProgettiService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ProgettiDto>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping
    public ResponseEntity<Boolean> crea(@RequestBody ProgettiDto progetto) {
        return ResponseEntity.ok(service.salva(progetto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProgettiDto> getById(@PathVariable Long id) {
        return  ResponseEntity.ok(service.findById(id));

    }

    @PutMapping("/{id}")
    public ResponseEntity<ProgettiDto> aggiorna(@PathVariable Long id, @RequestBody ProgettiDto progetto) {
        ProgettiDto aggiornato = service.aggiorna(id, progetto);
        if (aggiornato != null) {
            return ResponseEntity.ok(aggiornato);
        }
        return ResponseEntity.notFound().build();
    }

}
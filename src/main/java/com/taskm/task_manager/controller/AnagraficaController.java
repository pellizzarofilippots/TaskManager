package com.taskm.task_manager.controller;

import com.taskm.task_manager.dto.AnagraficaDto;
import com.taskm.task_manager.service.AnagraficaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/anagrafica")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class AnagraficaController {

    private final AnagraficaService anagraficaService;

    @Autowired
    public AnagraficaController(AnagraficaService anagraficaService) {
        this.anagraficaService = anagraficaService;
    }

    // -----------------------------
    // GET: tutte le anagrafiche
    // -----------------------------
    @GetMapping
    public ResponseEntity<List<AnagraficaDto>> getAll() {
        return ResponseEntity.ok(anagraficaService.findAll());
    }

    // -----------------------------
    // GET: anagrafica per id
    // -----------------------------
    @GetMapping("/{id}")
    public ResponseEntity<AnagraficaDto> getById(@PathVariable Long id) {
        return anagraficaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // -----------------------------
    // POST: crea nuova anagrafica
    // -----------------------------
    @PostMapping
    public ResponseEntity<AnagraficaDto> create(@RequestBody AnagraficaDto dto) {

        System.out.println("DTO Anagrafica ricevuto: " + dto); // DEBUG

        AnagraficaDto created = anagraficaService.create(dto);
        return ResponseEntity.ok(created);
    }

    // -----------------------------
    // PUT: aggiorna anagrafica
    // -----------------------------
    @PutMapping("/{id}")
    public ResponseEntity<AnagraficaDto> update(
            @PathVariable Long id,
            @RequestBody AnagraficaDto dto) {

        if (!id.equals(dto.getId())) {
            return ResponseEntity.badRequest().body(null);
        }

        AnagraficaDto updated = anagraficaService.update(dto);
        return ResponseEntity.ok(updated);
    }

}

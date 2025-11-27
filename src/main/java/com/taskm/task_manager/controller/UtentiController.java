package com.taskm.task_manager.controller;

import com.taskm.task_manager.dto.UtentiDto;
import com.taskm.task_manager.model.Utenti;
import com.taskm.task_manager.service.UtentiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/utenti")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class UtentiController {

    private final UtentiService utentiService;

    @Autowired
    public UtentiController(UtentiService utentiService) {
        this.utentiService = utentiService;
    }

    // -----------------------------
    // GET: tutti gli utenti
    // -----------------------------
    @GetMapping
    public ResponseEntity<List<UtentiDto>> getAll() {
        return ResponseEntity.ok(utentiService.findAll());
    }

    // -----------------------------
    // GET: utente per id
    // -----------------------------
    @GetMapping("/id/{id}")
    public ResponseEntity<UtentiDto> getById(@PathVariable Long id) {
        return utentiService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // -----------------------------
    // POST: crea nuovo utente
    // -----------------------------
    @PostMapping
    public ResponseEntity<UtentiDto> create(@RequestBody UtentiDto dto) {
// stampa per debug
        System.out.println("DTO ricevuto: " + dto);
        System.out.println("STATO IN ARRIVO = " + dto.getStatoUtenteId());
        System.out.println("RUOLO IN ARRIVO = " + dto.getRuoloId());


        UtentiDto created = utentiService.create(dto);
        return ResponseEntity.ok(created);
    }

    // -----------------------------
    // PUT: aggiorna utente
    // -----------------------------
    @PutMapping("/{id}")
    public ResponseEntity<UtentiDto> update(@PathVariable Long id, @RequestBody UtentiDto dto) {

        if (!id.equals(dto.getId())) {
            return ResponseEntity.badRequest().body(null);
        }

        UtentiDto updated = utentiService.update(dto);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<Long> getRuoloUtente(@PathVariable String username) {
        return utentiService.findByUsername(username)
                .map(u -> ResponseEntity.ok(u.getRuoloId()))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());

    }


}

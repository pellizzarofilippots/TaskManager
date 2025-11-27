package com.taskm.task_manager.controller;


import com.taskm.task_manager.model.RuoliUtente;
import com.taskm.task_manager.repository.RuoloUtenteRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/ruoliu")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class RuoliUtenteController {

    private final RuoloUtenteRepository ruoliUtenteRepository;

    // Spring inietterà automaticamente il repository
    public RuoliUtenteController(RuoloUtenteRepository ruoliUtenteRepository) {
        this.ruoliUtenteRepository = ruoliUtenteRepository;
    }


    @GetMapping
    public List<RuoliUtente> getAll() {
        return ruoliUtenteRepository.findAll();
    }
}

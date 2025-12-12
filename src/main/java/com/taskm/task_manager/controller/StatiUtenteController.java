package com.taskm.task_manager.controller;


import com.taskm.task_manager.model.StatiUtente;
import com.taskm.task_manager.repository.StatiUtenteRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/statiu")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class StatiUtenteController {

    private StatiUtenteRepository  statiUtenteRepository;

    public StatiUtenteController(StatiUtenteRepository statiUtenteRepository) {
        this.statiUtenteRepository = statiUtenteRepository;
    }

    @GetMapping
    public List<StatiUtente> getStatiUtente() {
        return statiUtenteRepository.findAll();
    }

}

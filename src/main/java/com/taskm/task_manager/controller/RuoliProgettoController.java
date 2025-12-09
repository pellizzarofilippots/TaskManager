package com.taskm.task_manager.controller;


import com.taskm.task_manager.model.RuoliProgetto;
import com.taskm.task_manager.repository.RuoliProgettoRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/ruoliProgetto")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class RuoliProgettoController {


    private RuoliProgettoRepository ruoliProgettoRepository;

    public RuoliProgettoController(RuoliProgettoRepository ruoliProgettoRepository) {
        this.ruoliProgettoRepository = ruoliProgettoRepository;
    }

    @GetMapping
    public List<RuoliProgetto> findAll() {return  ruoliProgettoRepository.findAll();}
}

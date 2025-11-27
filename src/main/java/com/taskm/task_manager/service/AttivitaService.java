package com.taskm.task_manager.service;


import com.taskm.task_manager.model.Attivita;
import com.taskm.task_manager.repository.AttivitaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AttivitaService {
    private final AttivitaRepository repository;

    public AttivitaService(AttivitaRepository repository) {
        this.repository = repository;
    }

    public List<Attivita> findAll() {
        return repository.findAll();
    }

    public List<Attivita> findByProgetto(Long idProgetto) {
        return repository.findByProgettoId(idProgetto);
    }

    public Attivita salva(Attivita attivita) {
        return repository.save(attivita);
    }

    public Optional<Attivita> findById(Long id) {
        return repository.findById(id);
    }

    public void elimina(Long id) {
        repository.deleteById(id);
    }
}
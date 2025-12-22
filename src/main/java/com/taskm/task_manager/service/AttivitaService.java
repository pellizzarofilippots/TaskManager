package com.taskm.task_manager.service;

import com.taskm.task_manager.model.Anagrafica;
import com.taskm.task_manager.model.Attivita;
import com.taskm.task_manager.model.Priorita;
import com.taskm.task_manager.repository.AnagraficaRepository;
import com.taskm.task_manager.repository.AttivitaRepository;
import com.taskm.task_manager.repository.PrioritàRepository;
import com.taskm.task_manager.repository.StatoAttivitàRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AttivitaService {
    private final AttivitaRepository repository;
    private final StatoAttivitàRepository  statoRepository;
    private final PrioritàRepository  prioritàRepository;
    private final AnagraficaRepository  anagraficaRepository;

    public AttivitaService(AttivitaRepository repository, StatoAttivitàRepository statoRepository, PrioritàRepository prioritàRepository,  AnagraficaRepository anagraficaRepository) {
        this.repository = repository;
        this.statoRepository = statoRepository;
        this.prioritàRepository = prioritàRepository;
        this.anagraficaRepository = anagraficaRepository;
    }

    public List<Attivita> findAll() {
        return repository.findAll();
    }

    public Optional<Attivita> findById(Long id) {
        return repository.findById(id);
    }

    public List<Attivita> findByProgetto(Long idProgetto) {
        return repository.findByProgetto_Id(idProgetto);
    }

//    public List<Attivita> findByPersona(Long personaId) {
//        return repository.findByOperatore_Id(personaId);
//    }

    public List<Attivita> findByOperatore(Long operatoreId) {
        return repository.findByOperatore_Id(operatoreId);
    }

    public List<Attivita> findByStato(Long statoId) {
        return repository.findByStato_Id(statoId);
    }

    public List<Attivita> findByPriorita(Long prioritaId) {
        return repository.findByPriorita_Id(prioritaId);
    }

    public Attivita salva(Attivita attivita) {
        return repository.save(attivita);
    }

    public Optional<Attivita> aggiornaStato(Long id, Long statoId) {
        return repository.findById(id)
                .map(attivita -> {
                    statoRepository.findById(statoId).ifPresent(attivita::setStato);
                    return repository.save(attivita);
                });
    }


    public Optional<Attivita> aggiornaPriorita(Long id, Long prioritaId) {
        Optional<Attivita> attivitaOpt = repository.findById(id);
        if (attivitaOpt.isPresent()) {
            Attivita attivita = attivitaOpt.get();
            Optional<Priorita> prioritaOpt = prioritàRepository.findById(prioritaId);
            if (prioritaOpt.isPresent()) {
                attivita.setPriorita(prioritaOpt.get());
                return Optional.of(repository.save(attivita));
            }
        }
        return Optional.empty();
    }
    public Optional<Attivita> assegnaPersona(Long id, Long personaId) {
        Optional<Attivita> attivitaOpt = repository.findById(id);
        if (attivitaOpt.isPresent()) {
            Attivita attivita = attivitaOpt.get();
            Optional<Anagrafica> personaOpt = anagraficaRepository.findById(personaId);
            if (personaOpt.isPresent()) {
                attivita.setOperatore(personaOpt.get());
                return Optional.of(repository.save(attivita));
            }
        }
        return Optional.empty();
    }

    public void elimina(Long id) {
        repository.deleteById(id);
    }

//    public List<Attivita> findByProgettoAndStato(Long progettoId, Long statoId) {
//        return repository.findByProgettoIdAndStatoId(progettoId, statoId);
//    }
//
//    public List<Attivita> findByProgettoAndPersona(Long progettoId, Long personaId) {
//        return repository.findByProgettoIdAndPersonaId(progettoId, personaId);
//    }
}
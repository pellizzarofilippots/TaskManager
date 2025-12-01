package com.taskm.task_manager.controller;

import com.taskm.task_manager.dto.AssegnazioneDto;
import com.taskm.task_manager.model.*;
import com.taskm.task_manager.repository.AnagraficaRepository;
import com.taskm.task_manager.repository.ProgettiRepository;
import com.taskm.task_manager.repository.ProgettiXAnagraficaRepository;
import com.taskm.task_manager.repository.RuoliProgettoRepository;
import jakarta.persistence.Column;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/aggiungipersone")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class AggiungiPersoneController {

    private ProgettiXAnagraficaRepository progettoXAnagrafica;
    private ProgettiRepository  progettiRepository;
    private AnagraficaRepository anagraficaRepository;
    private RuoliProgettoRepository ruoliProgettoRepository;

    public AggiungiPersoneController(ProgettiXAnagraficaRepository progettoXAnagrafica,
                                     ProgettiRepository  progettiRepository,
                                     AnagraficaRepository anagraficaRepository,
                                     RuoliProgettoRepository ruoliProgettoRepository) {
        this.progettoXAnagrafica = progettoXAnagrafica;
        this.progettiRepository = progettiRepository;
        this.anagraficaRepository = anagraficaRepository;
        this.ruoliProgettoRepository = ruoliProgettoRepository;
    }

//    @PostMapping
//    public AssegnazioneDto assegna(@RequestBody AssegnazioneDto assegna){
//        ProgettoXAnagrafica assegnazione = new ProgettoXAnagrafica();
//        Progetti progetto = progettiRepository.findById(assegna.getProgettoId());
//        Optional<Anagrafica> anagrafica=anagraficaRepository.findById(assegna.getPersonaId());
//
//        System.out.println(">>> RUOLO ID RICEVUTO DAL FRONTEND = " + assegna.getRuoloId());
//        Optional<RuoliProgetto> ruoliProgetto=ruoliProgettoRepository.findById(assegna.getRuoloId());
//
//        System.out.println(">>> RISULTATO FIND = " + ruoliProgetto);
//        assegnazione.setProgetto(progetto);
//        assegnazione.setPersona(anagrafica.get());
//        assegnazione.setRuolo(ruoliProgetto.get());
//        assegnazione.setHasAttAggiungi(assegna.getHasAttAggiungi());
//        assegnazione.setHasAttStato(assegna.getHasAttStato());
//        assegnazione.setHasAttPrendi(assegna.getHasAttPrendi());
//        assegnazione.setHasPrgGestisci(assegna.getHasPrgGestisci());
//        assegnazione.setHasAttAssegna(assegna.getHasAttAssegna());
//        progettoXAnagrafica.save(assegnazione);
//        return assegna;
//
//
//
//
//    }

    @PostMapping
    public AssegnazioneDto assegna(@RequestBody AssegnazioneDto assegna) {
        // Recupero progetto e persona
        Progetti progetto = progettiRepository.findById(assegna.getProgettoId());
        Optional<Anagrafica> anagraficaOpt = anagraficaRepository.findById(assegna.getPersonaId());

        if (progetto==null || anagraficaOpt.isEmpty()) {
            throw new IllegalArgumentException("Progetto o persona non trovati");
        }


        Anagrafica persona = anagraficaOpt.get();

        // Recupero ruolo (può essere opzionale)
        Optional<RuoliProgetto> ruoloOpt = ruoliProgettoRepository.findById(assegna.getRuoloId());
        RuoliProgetto ruolo = ruoloOpt.orElse(null);

        // Creo l'oggetto ProgettoXAnagrafica
        ProgettoXAnagrafica assegnazione = new ProgettoXAnagrafica();

        // Creo e setto l'ID composto
        ProgettoXAnagraficaId id = new ProgettoXAnagraficaId();
        id.setProgettoId(progetto.getId());
        id.setPersonaId(persona.getId());
        assegnazione.setId(id);

        // Associo le entità
        assegnazione.setProgetto(progetto);
        assegnazione.setPersona(persona);
        assegnazione.setRuolo(ruolo);

        // Setto permessi
        assegnazione.setHasPrgGestisci(assegna.getHasPrgGestisci());
        assegnazione.setHasAttAggiungi(assegna.getHasAttAggiungi());
        assegnazione.setHasAttAssegna(assegna.getHasAttAssegna());
        assegnazione.setHasAttStato(assegna.getHasAttStato());
        assegnazione.setHasAttPrendi(assegna.getHasAttPrendi());

        // Salvo l'assegnazione
        progettoXAnagrafica.save(assegnazione);

        return assegna;
    }

}

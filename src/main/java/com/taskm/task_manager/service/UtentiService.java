package com.taskm.task_manager.service;

import com.taskm.task_manager.model.Anagrafica;
import com.taskm.task_manager.model.RuoliUtente;
import com.taskm.task_manager.model.StatiUtente;
import com.taskm.task_manager.model.Utenti;
import com.taskm.task_manager.repository.RuoloUtenteRepository;
import com.taskm.task_manager.repository.StatiUtenteRepository;
import com.taskm.task_manager.repository.UtentiRepository;
// Assunto
import com.taskm.task_manager.dto.UtentiDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException; // Per le eccezioni sulle dipendenze
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UtentiService {

    private final UtentiRepository utentiRepository;
    private final RuoloUtenteRepository ruoliRepository;
    private final StatiUtenteRepository statiUtenteRepository;
    @PersistenceContext
    private EntityManager em;



    @Autowired
    public UtentiService(UtentiRepository utentiRepository,
                         RuoloUtenteRepository ruoliRepository,
                         StatiUtenteRepository statiUtenteRepository
                         ) {
        this.utentiRepository = utentiRepository;
        this.ruoliRepository = ruoliRepository;
        this.statiUtenteRepository = statiUtenteRepository;

    }




    public List<UtentiDto> findAll() {
        List<Utenti> utenti = utentiRepository.findAll();
        return utenti.stream()
                .map(utenti1 -> new UtentiDto(
                        // 1. Campi Semplici
                        utenti1.getId(),
                        utenti1.getUserid(),
                        utenti1.getPassword(),// Mappa userid (Entità) a userId (DTO)
                        utenti1.getDataScadenzaPwd(),
                        utenti1.getCodiceAttivazione(),
                        utenti1.getTentativiFalliti(),
                        utenti1.getForzaCambioPwd(),
                        utenti1.getStatoUtente().getId(),
                        utenti1.getRuolo().getId(),
                        utenti1.getAnagrafica().getId()

                        // NOTA: Aggiungi qui tutti gli altri campi che UtentiDto necessita nel costruttore
                ))
                .collect(Collectors.toList());

    }

    public Optional<UtentiDto> findById(Long id) {
        return utentiRepository.findById(id)
                .map(utenti1 -> new UtentiDto(
                        utenti1.getId(),
                        utenti1.getUserid(),
                        utenti1.getPassword(),
                        utenti1.getDataScadenzaPwd(),
                        utenti1.getCodiceAttivazione(),
                        utenti1.getTentativiFalliti(),
                        utenti1.getForzaCambioPwd(),
                        utenti1.getStatoUtente().getId(),
                        utenti1.getRuolo().getId(),
                        utenti1.getAnagrafica().getId()
                ));
    }

    public Optional<UtentiDto> findByUsername(String username) {
        return utentiRepository.findByUsername(username)
                .map(utenti1 -> new UtentiDto(
                        utenti1.getId(),
                        utenti1.getUserid(),
                        utenti1.getPassword(),
                        utenti1.getDataScadenzaPwd(),
                        utenti1.getCodiceAttivazione(),
                        utenti1.getTentativiFalliti(),
                        utenti1.getForzaCambioPwd(),
                        utenti1.getStatoUtente().getId(),
                        utenti1.getRuolo().getId(),
                        utenti1.getAnagrafica().getId()
                ));
    }


    public UtentiDto create(UtentiDto utenteDto) {
        Anagrafica anagrafica = em.find(Anagrafica.class, utenteDto.getAnagraficaId());
        if (anagrafica == null) {
            throw new NoSuchElementException("Anagrafica non trovata con ID: " + utenteDto.getAnagraficaId());
        }

        Utenti nuovo = new Utenti();
        nuovo.setUserid(utenteDto.getUserId());
        nuovo.setPassword(utenteDto.getPassword());
        nuovo.setDataScadenzaPwd(utenteDto.getDataScadenzaPwd());
        nuovo.setCodiceAttivazione(utenteDto.getCodiceAttivazione());
        nuovo.setTentativiFalliti(utenteDto.getTentativiFalliti());
        nuovo.setForzaCambioPwd(utenteDto.getForzaCambioPwd());
        nuovo.setAnagrafica(anagrafica);

// Recuperi il ruolo dal DB usando l'id che arriva dal frontend
        RuoliUtente ruolo = ruoliRepository.findById(utenteDto.getRuoloId());

        nuovo.setRuolo(ruolo);
        StatiUtente statiUtente = statiUtenteRepository.findById(utenteDto.getStatoUtenteId());

        nuovo.setStatoUtente(statiUtente);

        // Salvataggio semplice tramite repository
        Utenti salvato = utentiRepository.create(nuovo);

        return new UtentiDto(
                salvato.getId(),
                salvato.getUserid(),
                salvato.getPassword(),
                salvato.getDataScadenzaPwd(),
                salvato.getCodiceAttivazione(),
                salvato.getTentativiFalliti(),
                salvato.getForzaCambioPwd(),
                salvato.getStatoUtente().getId(),
                salvato.getRuolo().getId(),
                salvato.getAnagrafica().getId()
        );
    }
    public UtentiDto update(UtentiDto utenteDto) {
        Utenti esistente = utentiRepository.findById(utenteDto.getId())
                .orElseThrow(() -> new NoSuchElementException("Utente non trovato con ID: " + utenteDto.getId()));

        esistente.setUserid(utenteDto.getUserId());
        esistente.setDataScadenzaPwd(utenteDto.getDataScadenzaPwd());
        esistente.setCodiceAttivazione(utenteDto.getCodiceAttivazione());
        esistente.setTentativiFalliti(utenteDto.getTentativiFalliti());
        esistente.setForzaCambioPwd(utenteDto.getForzaCambioPwd());


        Utenti aggiornato = utentiRepository.create(esistente);

        return new UtentiDto(
                aggiornato.getId(),
                aggiornato.getUserid(),
                aggiornato.getPassword(),
                aggiornato.getDataScadenzaPwd(),
                aggiornato.getCodiceAttivazione(),
                aggiornato.getTentativiFalliti(),
                aggiornato.getForzaCambioPwd(),
                aggiornato.getStatoUtente().getId(),
                aggiornato.getRuolo().getId(),
                aggiornato.getAnagrafica().getId()
        );
    }}
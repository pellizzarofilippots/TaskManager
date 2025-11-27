package com.taskm.task_manager.service;

import com.taskm.task_manager.dto.AnagraficaDto;
import com.taskm.task_manager.model.Anagrafica;
import com.taskm.task_manager.repository.AnagraficaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AnagraficaService {

    private final AnagraficaRepository anagraficaRepository;

    @PersistenceContext
    private EntityManager em;

    @Autowired
    public AnagraficaService(AnagraficaRepository anagraficaRepository) {
        this.anagraficaRepository = anagraficaRepository;
    }

    // FIND ALL
    public List<AnagraficaDto> findAll() {
        List<Anagrafica> lista = anagraficaRepository.findAll();

        return lista.stream()
                .map(a -> new AnagraficaDto(
                        a.getId(),
                        a.getNome(),
                        a.getCognome(),
                        a.getGenere(),
                        a.getNascita(),
                        a.getCf(),
                        a.getIndCanc()
                ))
                .collect(Collectors.toList());
    }

    // FIND BY ID
    public Optional<AnagraficaDto> findById(Long id) {
        return anagraficaRepository.findById(id)
                .map(a -> new AnagraficaDto(
                        a.getId(),
                        a.getNome(),
                        a.getCognome(),
                        a.getGenere(),
                        a.getNascita(),
                        a.getCf(),
                        a.getIndCanc()
                ));
    }

    // CREATE
    public AnagraficaDto create(AnagraficaDto dto) {

        Anagrafica nuova = new Anagrafica();
        nuova.setNome(dto.getNome());
        nuova.setCognome(dto.getCognome());
        nuova.setGenere(dto.getGenere());
        nuova.setNascita(dto.getNascita());
        nuova.setCf(dto.getCf());
        nuova.setIndCanc(dto.getIndCanc());

        Anagrafica salvata = anagraficaRepository.create(nuova);

        return new AnagraficaDto(
                salvata.getId(),
                salvata.getNome(),
                salvata.getCognome(),
                salvata.getGenere(),
                salvata.getNascita(),
                salvata.getCf(),
                salvata.getIndCanc()
        );
    }

    // UPDATE
    public AnagraficaDto update(AnagraficaDto dto) {

        Anagrafica esistente = anagraficaRepository.findById(dto.getId())
                .orElseThrow(() -> new NoSuchElementException(
                        "Anagrafica non trovata con ID: " + dto.getId()
                ));

        esistente.setNome(dto.getNome());
        esistente.setCognome(dto.getCognome());
        esistente.setGenere(dto.getGenere());
        esistente.setNascita(dto.getNascita());
        esistente.setCf(dto.getCf());
        esistente.setIndCanc(dto.getIndCanc());

        Anagrafica aggiornata = anagraficaRepository.update(esistente);

        return new AnagraficaDto(
                aggiornata.getId(),
                aggiornata.getNome(),
                aggiornata.getCognome(),
                aggiornata.getGenere(),
                aggiornata.getNascita(),
                aggiornata.getCf(),
                aggiornata.getIndCanc()
        );
    }
}

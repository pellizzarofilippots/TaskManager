package com.taskm.task_manager.service;

import com.taskm.task_manager.dto.ProgettiDto;
import com.taskm.task_manager.model.Anagrafica;
import com.taskm.task_manager.model.Progetti;
import com.taskm.task_manager.repository.AnagraficaRepository;
import com.taskm.task_manager.repository.ProgettiRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProgettiService {
    private final ProgettiRepository repository;

   private AnagraficaRepository  anagraficaRepository;

    public ProgettiService(ProgettiRepository repository, AnagraficaRepository anagraficaRepository) {
        this.repository = repository;
        this.anagraficaRepository=anagraficaRepository;
    }

    public List<ProgettiDto> findAll() {
        List<Progetti> progetti = repository.findAll();
        return progetti.stream()
                .map(p -> new ProgettiDto(p.getNome(), p.getDescrizione(), p.getInizio(), p.getFine(),p.getResponsabile().getId(), p.getIndCanc(), p.getModDate()))
                .collect(Collectors.toList());
    }

    public ProgettiDto findById(Long id) {
        Progetti progetti = repository.findById(id);
        return new ProgettiDto(progetti.getNome(), progetti.getDescrizione(), progetti.getInizio(), progetti.getFine(), progetti.getResponsabile().getId(),progetti.getIndCanc(), progetti.getModDate());
    }

    public boolean  salva(ProgettiDto progetto) {
        Progetti progetti = new Progetti();
        progetti.setNome(progetto.getNome());
        progetti.setDescrizione(progetto.getDescrizione());
        progetti.setInizio(progetto.getInizio());
        progetti.setFine(progetto.getFine());
        Optional<Anagrafica> a =anagraficaRepository.findById(progetto.getResponsabileId());
        progetti.setResponsabile(a.get());

        progetto.setIndCanc(0);
        progetto.setModDate(LocalDate.now());

        try {
            repository.save(progetti);
            return true;
        }catch (Exception e){

            e.printStackTrace();
            return false;
        }
    }


}
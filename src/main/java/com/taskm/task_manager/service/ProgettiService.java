package com.taskm.task_manager.service;

import com.taskm.task_manager.dto.AssegnazioneDto;
import com.taskm.task_manager.dto.ProgettiDto;
import com.taskm.task_manager.model.Anagrafica;
import com.taskm.task_manager.model.Progetti;
import com.taskm.task_manager.model.ProgettoXAnagrafica;
import com.taskm.task_manager.repository.AnagraficaRepository;
import com.taskm.task_manager.repository.ProgettiRepository;
import com.taskm.task_manager.repository.ProgettiXAnagraficaRepository;
import com.taskm.task_manager.repository.RuoliProgettoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProgettiService {
    private final ProgettiRepository repository;

   private AnagraficaRepository  anagraficaRepository;
   private RuoliProgettoRepository ruoliProgettoRepository;
   private ProgettiXAnagraficaRepository progettiXAnagraficaRepository;

    public ProgettiService(ProgettiRepository repository,
                           AnagraficaRepository anagraficaRepository,
                           RuoliProgettoRepository  ruoliProgettoRepository,
                           ProgettiXAnagraficaRepository progettiXAnagraficaRepository) {
        this.repository = repository;
        this.anagraficaRepository=anagraficaRepository;
        this.ruoliProgettoRepository=ruoliProgettoRepository;
        this.progettiXAnagraficaRepository=progettiXAnagraficaRepository;
    }

//    public List<ProgettiDto> findAll() {
//        List<Progetti> progetti = repository.findAll();
//        return progetti.stream()
//                .map(p -> new ProgettiDto(p.getNome(), p.getDescrizione(), p.getInizio(), p.getFine(),p.getResponsabile().getId(), p.getIndCanc(), p.getModDate()))
//                .collect(Collectors.toList());
//    }

    public List<ProgettiDto> findAll() {
        List<Progetti> progetti = repository.findAll();

        return progetti.stream().map(p -> {
            ProgettiDto dto = new ProgettiDto();

            dto.setId(p.getId());
            dto.setNome(p.getNome());
            dto.setDescrizione(p.getDescrizione());
            dto.setInizio(p.getInizio());
            dto.setFine(p.getFine());
            dto.setResponsabileId(p.getResponsabile().getId());
            dto.setIndCanc(p.isIndCanc());
            dto.setModDate(p.getModDate());

            // Se vuoi popolare le assegnazioni, qui
            dto.setAssegnazioni(null); // o lista mappata

            return dto;
        }).collect(Collectors.toList());
    }


    //    public ProgettiDto findById(Long id) {
//        Progetti progetti = repository.findById(id);
//        return new ProgettiDto()
//        progetti.getNome(),
//                progetti.getDescrizione(),
//                progetti.getInizio(),
//                progetti.getFine(),
//                progetti.getResponsabile().getId(),
//                progetti.getIndCanc(),
//                progetti.getModDate()
//                //,progetti.get
//        )
//                ;
//    }
public ProgettiDto findById(Long id) {

    Progetti p = repository.findById(id);

    ProgettiDto dto = new ProgettiDto(); // NON usare più il costruttore lungo

    dto.setId(p.getId());
    dto.setNome(p.getNome());
    dto.setDescrizione(p.getDescrizione());
    dto.setInizio(p.getInizio());
    dto.setFine(p.getFine());
    dto.setResponsabileId(p.getResponsabile().getId());
    dto.setIndCanc(p.isIndCanc());
    dto.setModDate(p.getModDate());

    // Ora il setter ESISTE e viene trovato
    dto.setAssegnazioni(null); // o quello che vuoi

    return dto;
}

    public boolean  salva(ProgettiDto progetto) {
        Progetti progetti = new Progetti();

        progetti.setNome(progetto.getNome());
        progetti.setDescrizione(progetto.getDescrizione());
        progetti.setInizio(progetto.getInizio());
        progetti.setFine(progetto.getFine());
        Optional<Anagrafica> a =anagraficaRepository.findById(progetto.getResponsabileId());
        progetti.setResponsabile(a.get());


        progetto.setIndCanc(false);
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
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
            //dto.setResponsabileId(p.getResponsabile().getId());
            dto.setIndCanc(p.isIndCanc());
            dto.setModDate(p.getModDate());

            List<ProgettoXAnagrafica> assegnazioniEntity =
                    progettiXAnagraficaRepository.findByProgettoId(p.getId());
            List<AssegnazioneDto> assegnazioniDto = assegnazioniEntity.stream()
                    .map(pxa -> {
                        AssegnazioneDto aDto = new AssegnazioneDto();
                        aDto.setPersonaId(pxa.getPersona().getId());
                        aDto.setRuoloId(pxa.getRuolo() != null ? pxa.getRuolo().getId() : null);
                        aDto.setHasPrgGestisci(pxa.getHasPrgGestisci());
                        aDto.setHasAttAggiungi(pxa.getHasAttAggiungi());
                        aDto.setHasAttAssegna(pxa.getHasAttAssegna());
                        aDto.setHasAttStato(pxa.getHasAttStato());
                        aDto.setHasAttPrendi(pxa.getHasAttPrendi());
                        return aDto;
                    })
                    .collect(Collectors.toList());

            dto.setAssegnazioni(assegnazioniDto);

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
  //  dto.setResponsabileId(p.getResponsabile().getId());
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
//        Optional<Anagrafica> a =anagraficaRepository.findById(progetto.getResponsabileId());
       // progetti.setResponsabile(a.get());


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

    public ProgettiDto aggiorna(Long id, ProgettiDto progettoDto) {
        Progetti esistente = repository.findById(id);

        if (esistente == null) {
            return null;
        }

        // Aggiorna i campi
        esistente.setNome(progettoDto.getNome());
        esistente.setDescrizione(progettoDto.getDescrizione());
        esistente.setInizio(progettoDto.getInizio());
        esistente.setFine(progettoDto.getFine());

//        // Se cambia il responsabile
//        if (progettoDto.getResponsabileId() != null) {
//            Optional<Anagrafica> responsabile = anagraficaRepository.findById(progettoDto.getResponsabileId());
//            if (responsabile.isPresent()) {
//                esistente.setResponsabile(responsabile.get());
//            }
//        }

        esistente.setModDate(LocalDate.now());

        try {
            Progetti salvato = repository.save(esistente);

            // Converti in DTO e ritorna
            ProgettiDto dto = new ProgettiDto();
            dto.setId(salvato.getId());
            dto.setNome(salvato.getNome());
            dto.setDescrizione(salvato.getDescrizione());
            dto.setInizio(salvato.getInizio());
            dto.setFine(salvato.getFine());
        //    dto.setResponsabileId(salvato.getResponsabile().getId());
            dto.setIndCanc(salvato.isIndCanc());
            dto.setModDate(salvato.getModDate());
            dto.setAssegnazioni(null);

            return dto;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
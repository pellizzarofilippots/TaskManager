// ============================================
package com.taskm.task_manager.repository;

import com.taskm.task_manager.model.Attivita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
//public interface AttivitaRepository extends JpaRepository<Attivita, Long> {
//
//    // Query base esistenti
//    List<Attivita> findByProgettoId(Long progettoId);
//    List<Attivita> findByOperatoreId(Long operatoreId);
//
//    // Nuove query aggiunte
//    List<Attivita> findByPersonaId(Long personaId);
//    List<Attivita> findByStatoId(Long statoId);
//    List<Attivita> findByPrioritaId(Long prioritaId);
//    List<Attivita> findByTipoAttivitaId(Long tipoAttivitaId);
//
//    // Query combinate
//    List<Attivita> findByProgettoIdAndStatoId(Long progettoId, Long statoId);
//    List<Attivita> findByProgettoIdAndPersonaId(Long progettoId, Long personaId);
//    List<Attivita> findByProgettoIdAndPrioritaId(Long progettoId, Long prioritaId);
//    List<Attivita> findByPersonaIdAndStatoId(Long personaId, Long statoId);
//
//    // Query per ordinamento
//    List<Attivita> findByProgettoIdOrderByInizioAsc(Long progettoId);
//    List<Attivita> findByProgettoIdOrderByFineAsc(Long progettoId);
//    List<Attivita> findByPersonaIdOrderByPrioritaIdDesc(Long personaId);
//}
@Repository
public interface AttivitaRepository extends JpaRepository<Attivita, Long> {

    // Query base
    List<Attivita> findByProgetto_Id(Long progettoId);
    List<Attivita> findByOperatore_Id(Long operatoreId);
    List<Attivita> findByStato_Id(Long statoId);
    List<Attivita> findByPriorita_Id(Long prioritaId);
    List<Attivita> findByTipo_Id(Long tipoId);

    // Query combinate
    List<Attivita> findByProgetto_IdAndStato_Id(Long progettoId, Long statoId);
    List<Attivita> findByProgetto_IdAndOperatore_Id(Long progettoId, Long operatoreId);
    List<Attivita> findByProgetto_IdAndPriorita_Id(Long progettoId, Long prioritaId);
    List<Attivita> findByOperatore_IdAndStato_Id(Long operatoreId, Long statoId);

    // Query per ordinamento
    List<Attivita> findByProgetto_IdOrderByInizioAsc(Long progettoId);
    List<Attivita> findByProgetto_IdOrderByFineAsc(Long progettoId);
    List<Attivita> findByOperatore_IdOrderByPriorita_IdDesc(Long operatoreId);
}

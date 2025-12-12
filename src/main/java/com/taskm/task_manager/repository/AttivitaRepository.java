// ============================================
package com.taskm.task_manager.repository;

import com.taskm.task_manager.model.Attivita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttivitaRepository extends JpaRepository<Attivita, Long> {

    // Query base esistenti
    List<Attivita> findByProgettoId(Long progettoId);
    List<Attivita> findByOperatoreId(Long operatoreId);

    // Nuove query aggiunte
    List<Attivita> findByPersonaId(Long personaId);
    List<Attivita> findByStatoId(Long statoId);
    List<Attivita> findByPrioritaId(Long prioritaId);
    List<Attivita> findByTipoAttivitaId(Long tipoAttivitaId);

    // Query combinate
    List<Attivita> findByProgettoIdAndStatoId(Long progettoId, Long statoId);
    List<Attivita> findByProgettoIdAndPersonaId(Long progettoId, Long personaId);
    List<Attivita> findByProgettoIdAndPrioritaId(Long progettoId, Long prioritaId);
    List<Attivita> findByPersonaIdAndStatoId(Long personaId, Long statoId);

    // Query per ordinamento
    List<Attivita> findByProgettoIdOrderByInizioAsc(Long progettoId);
    List<Attivita> findByProgettoIdOrderByFineAsc(Long progettoId);
    List<Attivita> findByPersonaIdOrderByPrioritaIdDesc(Long personaId);
}
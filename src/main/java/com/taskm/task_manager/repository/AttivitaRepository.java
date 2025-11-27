package com.taskm.task_manager.repository;

import com.taskm.task_manager.model.Attivita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttivitaRepository extends JpaRepository<Attivita, Long> {
    List<Attivita> findByProgettoId(Long progettoId);
    List<Attivita> findByOperatoreId(Long operatoreId);
}
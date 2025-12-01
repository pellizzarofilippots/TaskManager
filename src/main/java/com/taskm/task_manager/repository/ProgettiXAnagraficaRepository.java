package com.taskm.task_manager.repository;

import com.taskm.task_manager.model.ProgettoXAnagrafica;
import com.taskm.task_manager.model.ProgettoXAnagraficaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgettiXAnagraficaRepository extends JpaRepository<ProgettoXAnagrafica, ProgettoXAnagraficaId> {
}

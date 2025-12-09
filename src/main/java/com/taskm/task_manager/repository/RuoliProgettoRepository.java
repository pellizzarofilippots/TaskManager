package com.taskm.task_manager.repository;

import com.taskm.task_manager.model.RuoliProgetto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RuoliProgettoRepository extends JpaRepository<RuoliProgetto,Long> {


}

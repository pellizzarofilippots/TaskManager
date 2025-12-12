package com.taskm.task_manager.repository;

import com.taskm.task_manager.model.StatiAttivita;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatoAttivitàRepository extends JpaRepository<StatiAttivita,Long> {
}

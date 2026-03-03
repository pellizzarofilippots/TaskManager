package com.taskm.task_manager.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "RUOLI_PROGETTO", schema = "WKSP_TASKMAN")
public class RuoliProgetto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ruoli_seq")
    @SequenceGenerator(name = "ruoli_seq", sequenceName = "ruoli_seq", allocationSize = 1)
    @Column(name = "ID_RUOLO", nullable = false)
    private Long id;

    // ...existing code...

    //@ColumnDefault("0")
    //@Column(name = "IND_CANC")
    @Column(name = "IND_CANC", columnDefinition = "boolean default false")
    private boolean indCanc;

}
package com.taskm.task_manager.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
    @Column(name = "ID_RUOLO", nullable = false)
    private Long id;

    @Column(name = "ICONA", length = 150)
    private String icona;

    @Column(name = "ETICHETTA", nullable = false, length = 70)
    private String etichetta;

    @Column(name = "DESCRIZIONE", length = 4000)
    private String descrizione;

    //@ColumnDefault("0")
    //@Column(name = "IND_CANC")
    @Column(name = "ind_canc", columnDefinition = "boolean default false")

    private boolean indCanc;

}
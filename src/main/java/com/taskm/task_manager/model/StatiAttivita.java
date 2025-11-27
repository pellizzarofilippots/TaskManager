package com.taskm.task_manager.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "STATI_ATTIVITA", schema = "WKSP_TASKMAN", uniqueConstraints = {
        @UniqueConstraint(name = "UNQ_STATI_ORDINE", columnNames = {"ORDINE"})
})
public class StatiAttivita {
    @Id
    @Column(name = "ID_STATO_ATTIVITA", nullable = false)
    private Long id;

    @Column(name = "ICONA", length = 150)
    private String icona;

    @Column(name = "ETICHETTA", length = 30)
    private String etichetta;

    @Column(name = "ORDINE")
    private Long ordine;

    @ColumnDefault("0")
    @Column(name = "IS_INATTIVA")
    private Boolean isInattiva;

}
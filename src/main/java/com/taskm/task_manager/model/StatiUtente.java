package com.taskm.task_manager.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "STATI_UTENTE", schema = "WKSP_TASKMAN", uniqueConstraints = {
        @UniqueConstraint(name = "UNQ_STATI_UTENTE_ETICHETTA", columnNames = {"ETICHETTA"})
})
public class StatiUtente {
    @Id
    @Column(name = "ID_STATO", nullable = false)
    private Long id;

    @Column(name = "ETICHETTA", nullable = false, length = 20)
    private String etichetta;

    @Column(name = "DESCRIZIONE", length = 4000)
    private String descrizione;

}
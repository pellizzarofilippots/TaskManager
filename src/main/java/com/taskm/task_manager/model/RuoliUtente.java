package com.taskm.task_manager.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "RUOLI_UTENTE", schema = "WKSP_TASKMAN")
public class RuoliUtente {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ruoli_seq")
    @SequenceGenerator(name = "ruoli_seq", sequenceName = "RUOLI_SEQ", allocationSize = 1)
    @Column(name = "ID_RUOLO", nullable = false)
    private Long id;

    @Column(name = "NOME_RUOLO", nullable = false, length = 50, unique = true)
    private String nomeRuolo;

    @Column(name = "DESCRIZIONE", length = 255)
    private String descrizione;


}
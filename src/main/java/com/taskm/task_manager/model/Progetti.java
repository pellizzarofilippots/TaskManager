package com.taskm.task_manager.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "PROGETTI", schema = "WKSP_TASKMAN")
public class Progetti {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PROGETTO", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "RESPONSABILE_ID")
    private Anagrafica responsabile;

    @Column(name = "NOME", nullable = false, length = 250)
    private String nome;

    @Column(name = "DESCRIZIONE", length = 4000)
    private String descrizione;

    @Column(name = "INIZIO", nullable = false)
    private LocalDate inizio;

    @Column(name = "FINE")
    private LocalDate fine;

    //@ColumnDefault("0")
    //@Column(name = "IND_CANC")
    @Column(name = "ind_canc", columnDefinition = "boolean default false")
    private boolean indCanc;

    @ColumnDefault("SYSDATE")
    @Column(name = "MOD_DATE")
    private LocalDate modDate;


}
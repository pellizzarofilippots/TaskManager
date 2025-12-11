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
@Table(name = "ATTIVITA", schema = "WKSP_TASKMAN")
public class Attivita {
    @Id
    @Column(name = "ID_ATTIVITA", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "PROGETTO_ID")
    private Progetti progetto;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "STATO_ID")
    private StatiAttivita stato;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "PRIORITA_ID")
    private Priorita priorita;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "OPERATORE_ID")
    private Anagrafica operatore;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "TIPO_ID")
    private TipiAttivita tipo;

    @Column(name = "NOME", length = 250)
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

    private Boolean indCanc;

    @ColumnDefault("SYSDATE")
    @Column(name = "MOD_DATE", nullable = false)

    private LocalDate modDate;

}
package com.taskm.task_manager.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "ESECUZIONE", schema = "WKSP_TASKMAN")
public class Esecuzione {
    @Id
    @Column(name = "ID_ESECUZIONE", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "ATTIVITA_ID", nullable = false)
    private Attivita attivita;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "OPERATORE_ID", nullable = false)
    private Anagrafica operatore;

    @Column(name = "\"DATA\"")
    private LocalDate data;

    @Column(name = "DURATA", precision = 5, scale = 2)
    private BigDecimal durata;

    @Column(name = "NOTE", length = 4000)
    private String note;

    //@ColumnDefault("0")
   // @Column(name = "IND_CANC")
    @Column(name = "ind_canc", columnDefinition = "boolean default false")
    private Boolean indCanc;

    @ColumnDefault("SYSDATE")
    @Column(name = "MOD_DATE", nullable = false)
    private LocalDate modDate;

}
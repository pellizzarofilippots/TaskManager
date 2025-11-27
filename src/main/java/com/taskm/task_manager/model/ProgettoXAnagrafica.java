package com.taskm.task_manager.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "PROGETTO_X_ANAGRAFICA", schema = "WKSP_TASKMAN")
public class ProgettoXAnagrafica {
    @EmbeddedId
    private ProgettoXAnagraficaId id;

    @MapsId("progettoId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "PROGETTO_ID", nullable = false)
    private Progetti progetto;

    @MapsId("personaId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "PERSONA_ID", nullable = false)
    private Anagrafica persona;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "RUOLO_ID")
    private RuoliProgetto ruolo;

    @Column(name = "HAS_PRG_GESTISCI")
    private Boolean hasPrgGestisci;

    @Column(name = "HAS_ATT_AGGIUNGI")
    private Boolean hasAttAggiungi;

    @Column(name = "HAS_ATT_ASSEGNA")
    private Boolean hasAttAssegna;

    @Column(name = "HAS_ATT_STATO")
    private Boolean hasAttStato;

    @Column(name = "HAS_ATT_PRENDI")
    private Boolean hasAttPrendi;

}
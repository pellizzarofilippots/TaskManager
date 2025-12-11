package com.taskm.task_manager.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "DIPENDENTI", schema = "WKSP_TASKMAN", uniqueConstraints = {
        @UniqueConstraint(name = "PK_DIPENDENTI_MATRICOLA", columnNames = {"MATRICOLA"}),
        @UniqueConstraint(name = "UNQ_DIPENDENTI_EMAIL", columnNames = {"EMAIL_AZIENDALE"})
})
public class Dipendenti {
    @EmbeddedId
    private DipendentiId id;

    @MapsId("idDipendente")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "ID_DIPENDENTE", nullable = false)
    private Anagrafica idDipendente;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "DITTA_ID")
    private Ditte ditta;

    @Column(name = "MATRICOLA", nullable = false, length = 10)
    private String matricola;

    @Column(name = "EMAIL_AZIENDALE", nullable = false, length = 180)
    private String emailAziendale;

    @Column(name = "DATA_CESSAZIONE", nullable = false)
    private LocalDate dataCessazione;

    @Column(name = "TIPO_CONTRATTO", length = 100)
    private String tipoContratto;

    @Column(name = "MANSIONE", length = 40)
    private String mansione;

    @Column(name = "LIVELLO_INQUADRAMENTO")
    private Boolean livelloInquadramento;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "REPARTO_ID", nullable = false)
    private Reparti reparto;

    @ManyToOne(fetch = FetchType.LAZY)
   @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "RESPONSABILE_ID")
    private Anagrafica responsabile;

}
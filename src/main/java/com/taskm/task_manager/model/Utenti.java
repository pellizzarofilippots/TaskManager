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
@Table(name = "UTENTI", schema = "WKSP_TASKMAN", uniqueConstraints = {
        @UniqueConstraint(name = "UNQ_UTENTI_USERID", columnNames = {"USERID"})
})
public class Utenti {
    @Id
    @Column(name = "ID_UTENTE", nullable = false)
    private Long id;

    @MapsId
    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "ID_UTENTE", nullable = false)
    private Anagrafica anagrafica;

    @Column(name = "USERID", nullable = false, length = 100)
    private String userid;

    @Column(name = "PASSWORD", nullable = false, length = 100)
    private String password;

    @Column(name = "DATA_SCADENZA_PWD", nullable = false)
    private LocalDate dataScadenzaPwd;

    @Column(name = "CODICE_ATTIVAZIONE", length = 16)
    private String codiceAttivazione;

    @ColumnDefault("0")
    @Column(name = "TENTATIVI_FALLITI")
    private Long tentativiFalliti;

    @ColumnDefault("1")
    @Column(name = "FORZA_CAMBIO_PWD")
    private Boolean forzaCambioPwd;

    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "STATO_UTENTE_ID")
    private StatiUtente statoUtente;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "RUOLO_ID", nullable = false)
    private RuoliUtente ruolo;

}
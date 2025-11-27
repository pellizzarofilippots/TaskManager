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
@Table(name = "SEDI", schema = "WKSP_TASKMAN")
public class Sedi {
    @Id
    @Column(name = "ID_SEDE", nullable = false)
    private Long id;

    @Column(name = "DENOMINAZIONE", length = 150)
    private String denominazione;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "DITTA_ID")
    private Ditte ditta;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "TIPO_SEDE_ID")
    private TipiSede tipoSede;

    @Column(name = "INDIRIZZO", length = 110)
    private String indirizzo;

    @Column(name = "LOCALITA_ID", length = 4)
    private String localitaId;

    @Column(name = "CAP", length = 5)
    private String cap;

    @ColumnDefault("0")
    @Column(name = "IS_ESTERNA")
    private Boolean isEsterna;

    @ColumnDefault("0")
    @Column(name = "IND_CANC")
    private Boolean indCanc;

    @ColumnDefault("SYSDATE")
    @Column(name = "MOD_DATE", nullable = false)
    private LocalDate modDate;

}
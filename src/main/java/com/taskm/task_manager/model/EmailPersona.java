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
@Table(name = "EMAIL_PERSONA", schema = "WKSP_TASKMAN", uniqueConstraints = {
        @UniqueConstraint(name = "UNQ_EMAIL_PERSONA_EMAIL", columnNames = {"EMAIL"})
})
public class EmailPersona {
    @Id
    @Column(name = "ID_RECAPITO", nullable = false)
    private Long id;

    @Column(name = "EMAIL", nullable = false, length = 180)
    private String email;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "PERSONA_ID", nullable = false)
    private Anagrafica persona;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "TIPO_ID", nullable = false)
    private TipiEmail tipo;

    @ColumnDefault("0")
    @Column(name = "IS_PEC")
    private Boolean isPec;

    @ColumnDefault("0")
    @Column(name = "IS_SCADUTA")
    private Boolean isScaduta;

    @ColumnDefault("0")
    @Column(name = "IND_CANC")
    private Boolean indCanc;

    @ColumnDefault("SYSDATE")
    @Column(name = "MOD_DATE", nullable = false)
    private LocalDate modDate;

}
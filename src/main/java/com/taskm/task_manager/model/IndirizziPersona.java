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
@Table(name = "INDIRIZZI_PERSONA", schema = "WKSP_TASKMAN")
public class IndirizziPersona {
    @Id
    @Column(name = "ID_RECAPITO", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "PERSONA_ID", nullable = false)
    private Anagrafica persona;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "TIPO_ID", nullable = false)
    private TipiIndirizzo tipo;

    @Column(name = "INDIRIZZO", length = 150)
    private String indirizzo;

    @Column(name = "TELEFONO", length = 20)
    private String telefono;

    @Column(name = "COD_COMUNE", length = 20)
    private String codComune;

    @Column(name = "CAP", length = 5)
    private String cap;

    //@ColumnDefault("0")
    //@Column(name = "IND_CANC")
    @Column(name = "ind_canc", columnDefinition = "boolean default false")

    private Boolean indCanc;

    @ColumnDefault("SYSDATE")
    @Column(name = "MOD_DATE", nullable = false)


    private LocalDate modDate;

}
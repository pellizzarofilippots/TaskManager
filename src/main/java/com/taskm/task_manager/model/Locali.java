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
@Table(name = "LOCALI", schema = "WKSP_TASKMAN")
public class Locali {
    @Id
    @Column(name = "ID_LOCALE", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "SEDE_ID")
    private Sedi sede;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "TIPO_LOCALE_ID")
    private TipiLocale tipoLocale;

    @Column(name = "NOME", length = 70)
    private String nome;

    @Column(name = "MAPPA")
    private byte[] mappa;

    //@ColumnDefault("0")
   // @Column(name = "IND_CANC")
    @Column(name = "ind_canc", columnDefinition = "boolean default false")

    private Boolean indCanc;

    @ColumnDefault("SYSDATE")
    //@Column(name = "MOD_DATE", nullable = false)


    private LocalDate modDate;

}
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
@Table(name = "REPARTI", schema = "WKSP_TASKMAN")
public class Reparti {
    @Id
    @Column(name = "ID_REPARTO", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "RESPONSABILE_ID")
    private Anagrafica responsabile;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "DITTA_ID", nullable = false)
    private Ditte ditta;

    @Column(name = "NOME", nullable = false, length = 80)
    private String nome;

    @ColumnDefault("0")
    @Column(name = "IND_CANC")
    private Boolean indCanc;

    @ColumnDefault("SYSDATE")
    @Column(name = "MOD_DATE", nullable = false)
    private LocalDate modDate;

}
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
@Table(name = "POSTAZIONI", schema = "WKSP_TASKMAN")
public class Postazioni {
    @Id
    @Column(name = "ID_POSTAZIONE", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "LOCALI_ID")
    private Locali locali;

    @Column(name = "CODICE", length = 10)
    private String codice;

    @ColumnDefault("0")
    @Column(name = "IND_CANC")
    private Boolean indCanc;

    @ColumnDefault("SYSDATE")
    @Column(name = "MOD_DATE", nullable = false)
    private LocalDate modDate;

}
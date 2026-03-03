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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "indirizzi_persona_seq")
    @SequenceGenerator(name = "indirizzi_persona_seq", sequenceName = "wksp_taskman.indirizzi_persona_seq", allocationSize = 1)
    @Column(name = "ID_RECAPITO", nullable = false)
    private Long id;

    // ...existing code...

    //@ColumnDefault("0")
    //@Column(name = "IND_CANC")
    @Column(name = "IND_CANC", columnDefinition = "boolean default false")
    private Boolean indCanc;

    @ColumnDefault("CURRENT_DATE")
    @Column(name = "MOD_DATE", nullable = false)

    private LocalDate modDate;

}
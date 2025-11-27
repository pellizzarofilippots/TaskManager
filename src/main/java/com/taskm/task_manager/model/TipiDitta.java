package com.taskm.task_manager.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "TIPI_DITTA", schema = "WKSP_TASKMAN", uniqueConstraints = {
        @UniqueConstraint(name = "UNQ_TM_TIPI_DITTA_CODICE", columnNames = {"CODICE"})
})
public class TipiDitta {
    @Id
    @Column(name = "ID_TIPO_DITTA", nullable = false)
    private Long id;

    @Column(name = "CODICE", nullable = false, length = 10)
    private String codice;

    @Column(name = "SIGLA", length = 10)
    private String sigla;

    @Column(name = "DESCRIZIONE", nullable = false, length = 100)
    private String descrizione;

    @ColumnDefault("0")
    @Column(name = "IND_CANC")
    private Boolean indCanc;

}